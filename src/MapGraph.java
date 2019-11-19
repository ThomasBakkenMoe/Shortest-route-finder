import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MapGraph {

    public void AStar(Node[] nodeArray, Edge[] edgeArray, Node startingNode, Node goalNode, boolean djikstra){

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
        System.out.println("Moving on to second stage!");

        System.out.println(goalNode.getPreviousNode());

        printResult(startingNode, goalNode);
    }

    private void expandNode(Node node, Node goalNode, PriorityQueue<Node> priorityQueue){

        System.out.println("Expanding: " + node);

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
                priorityQueue.add(edge.getToNode());
            }
        }
    }

    private void printResult(Node startingNode, Node goalNode){
        Node currentNode = goalNode;

        while (currentNode != goalNode){
            break;
        }
    }

    public static void main(String[] args) throws Exception{

        MapGraph mapGraph = new MapGraph();
        Loader loader = new Loader();

        Node[] nodeArray = loader.loadNodes("noder.txt");

        Edge[] edgeArray = loader.loadEdges("kanter.txt", nodeArray);

        mapGraph.AStar(nodeArray, edgeArray, nodeArray[0], nodeArray[2], false);
    }
}
