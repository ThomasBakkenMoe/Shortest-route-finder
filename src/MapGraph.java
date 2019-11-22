import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

public class MapGraph {

    int nodesChecked = 0;

    public void reset(Node[] nodeArray){

        for (Node node:nodeArray) {
            node.setPriority(1000000000);
            node.setCost(1000000000);
            node.setDiscovered(false);
            node.setExpanded(false);
            node.setDirectdistanceCalculated(false);
            node.setDirectDistance(0.0);
            node.setPreviousNode(null);

            nodesChecked = 0;
        }
    }

    public void AStar(Node startingNode, Node goalNode, String outputFile, boolean djikstra) throws Exception{


        Date start = new Date();
        Date slutt;

        Node currentNode;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(startingNode);
        startingNode.setCost(0);
        int iteration = 0;
        while(true){

            currentNode = priorityQueue.poll();

            if (currentNode == null){
                System.out.println("Current node is null! Iteration: " + iteration);
            }

            if (currentNode != goalNode){
                expandNode(currentNode, goalNode, priorityQueue, djikstra);
            }else{
                System.out.println("Checked " + nodesChecked + " nodes.");
                break;
            }

            iteration++;
        }
        
        //TODO: Actually create the second stage and end-printout :P
        //System.out.println("Moving on to second stage!");

        //System.out.println(goalNode.getPreviousNode());

        slutt = new Date();
        System.out.println("Time before print: " + (double) (slutt.getTime()-start.getTime()) + "ms");

        printResult(startingNode, goalNode, outputFile);
    }

    private void expandNode(Node node, Node goalNode, PriorityQueue<Node> priorityQueue, boolean djikstra){

        //System.out.println("Expanding: " + node);

        node.setExpanded(true);

        for (Edge edge: node.getOutgoingEdgeList()) {

            nodesChecked++;

            if (edge.getToNode().getCost() > node.getCost() + edge.getTime()){
                edge.getToNode().setCost(node.getCost() + edge.getTime());
                edge.getToNode().setPreviousNode(node);
            }else{
                continue;
            }

            if (djikstra){

                edge.getToNode().setPriority(edge.getToNode().getCost());

            }else {
                if (edge.getToNode().hasDirectdistanceCalculated()){
                    edge.getToNode().setPriority((int)(edge.getToNode().getDirectDistance() / 130 * 360000) /*Converts km to seconds of travel time*/ + edge.getToNode().getCost());
                }else {
                    edge.getToNode().calculateDirectDistanceToNode(goalNode);
                    edge.getToNode().setPriority((int)(edge.getToNode().getDirectDistance() / 130 * 360000) /*Converts km to seconds of travel time*/ + edge.getToNode().getCost());
                }
            }


            if (!edge.getToNode().isExpanded()){
                if (edge.getToNode().isDiscovered()){
                    priorityQueue.remove(edge.getToNode());
                }

                edge.getToNode().setDiscovered(true);
                priorityQueue.add(edge.getToNode());
            }
        }
    }

    private void printResult(Node startingNode, Node goalNode, String outputFile) throws Exception{
        Node currentNode = goalNode;
        Node prevNode;

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));

        int totalTime = goalNode.getCost(); // Time in seconds
        int totalDistance = 0;
        int amountOfNodes = 0;

        ArrayList<Node> nodePathList = new ArrayList<>();

        while (currentNode != startingNode){

            nodePathList.add(currentNode);

            //System.out.println("Adding node: " + currentNode.getNodeNum());

            prevNode = currentNode.getPreviousNode();

            if (prevNode == null){
                System.out.println("PrevNode is null!!!");
            }

            for (Edge edge: prevNode.getOutgoingEdgeList()) {
                if (edge.getToNode() == currentNode){
                    totalDistance += edge.getLength();
                }
            }

            amountOfNodes++;

            currentNode = prevNode;
        }

        int hours = totalTime / 360000;
        int minutes = (totalTime % 360000) / 6000;
        int seconds = (totalTime % 6000) / 100;

        bufferedWriter.write(Integer.toString(hours) + "t " + Integer.toString(minutes) + "m " + Integer.toString(seconds) + "s");
        bufferedWriter.newLine();
        bufferedWriter.write(Integer.toString(totalDistance / 1000) + "km");
        bufferedWriter.newLine();
        bufferedWriter.write("Nodes traversed: " + Integer.toString(amountOfNodes));
        bufferedWriter.newLine();

        for (int i = nodePathList.size() - 1; i >= 0; i--) {

            bufferedWriter.write(nodePathList.get(i).getLatitude() + "," + nodePathList.get(i).getLongitude());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }

    public static void main(String[] args) throws Exception{

        MapGraph mapGraph = new MapGraph();
        Loader loader = new Loader();

        Node[] nodeArray = loader.loadNodes("noder.txt");

        loader.loadEdges("kanter.txt", nodeArray);

        System.out.println("Beginning program");

        System.out.println("Djikstra");
        mapGraph.AStar(nodeArray[2617841], nodeArray[1491126], "outputDjikstra.txt", true);

        mapGraph.reset(nodeArray);

        System.out.println("A*");
        mapGraph.AStar(nodeArray[2617841], nodeArray[1491126], "output.txt", false);
    }
}