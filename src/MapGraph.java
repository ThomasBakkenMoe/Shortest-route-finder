import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

public class MapGraph {

    public void AStar(Node startingNode, Node goalNode, String outputFile, boolean djikstra) throws Exception{


        Date start = new Date();
        Date slutt;

        Node currentNode;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(startingNode);
        startingNode.setCost(0);
        while(true){

            currentNode = priorityQueue.poll();

            if (currentNode != null && currentNode != goalNode){
                expandNode(currentNode, goalNode, priorityQueue);
            }else{
                break;
            }
        }
        
        //TODO: Actually create the second stage and end-printout :P
        //System.out.println("Moving on to second stage!");

        //System.out.println(goalNode.getPreviousNode());

        slutt = new Date();
        System.out.println("Time before print: " + (double) (slutt.getTime()-start.getTime()) + "ms");

        printResult(startingNode, goalNode, outputFile);
    }

    private void expandNode(Node node, Node goalNode, PriorityQueue<Node> priorityQueue){

        //System.out.println("Expanding: " + node);

        node.setExpanded(true);

        for (Edge edge: node.getOutgoingEdgeList()) {

            if (edge.getToNode().getCost() > node.getCost() + edge.getTime()){
                edge.getToNode().setCost(node.getCost() + edge.getTime());
                edge.getToNode().setPreviousNode(node);
            }

            if (edge.getToNode().hasDirectdistanceCalculated()){
                edge.getToNode().setPriority((int)(edge.getToNode().getDirectDistance() / 1000 / 130 * 3600) + edge.getToNode().getCost());
            }else {
                edge.getToNode().calculateDirectDistanceToNode(goalNode);
                edge.getToNode().setPriority((int)(edge.getToNode().getDirectDistance() / 1000 / 130 * 3600) + edge.getToNode().getCost());
            }

            if (!edge.getToNode().isExpanded()){

                while (priorityQueue.contains(edge.getToNode())){
                    priorityQueue.remove(edge.getToNode());
                }
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

        ArrayList<Node> nodePathList = new ArrayList<>();

        while (currentNode != startingNode){

            nodePathList.add(currentNode);

            prevNode = currentNode.getPreviousNode();

            for (Edge edge: prevNode.getOutgoingEdgeList()) {
                if (edge.getToNode() == currentNode){
                    totalDistance += edge.getLength();
                }
            }

            currentNode = prevNode;
        }

        bufferedWriter.write(Integer.toString((totalTime / 3600)) + "t " + Integer.toString((totalTime % 3600) / 60) + "m " + Integer.toString((totalTime % 3600 % 60) / 60) + "s");
        bufferedWriter.newLine();
        bufferedWriter.write(Integer.toString(totalDistance / 1000) + "km");
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

        Edge[] edgeArray = loader.loadEdges("kanter.txt", nodeArray);

        System.out.println("Beginning program");

        mapGraph.AStar(nodeArray[2460904], nodeArray[2419175], "output.txt", false);
    }
}
