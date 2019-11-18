import java.util.ArrayList;
import java.util.Comparator;

public class MapGraph {

    private class SortByPriority implements Comparator<Node>{

        @Override
        public int compare(Node n1, Node n2) {
            return n1.getPriority() - n2.getPriority();
        }
    }

    public void AStar(Node[] nodeArray, Edge[] edgeArray, Node startingNode, Node goalNode){

        Node currentNode;
        ArrayList<Node> priorityQueue = new ArrayList<>();
        priorityQueue.add(startingNode);
        while(true){

            currentNode = priorityQueue.remove(0);

            if (currentNode != goalNode){
                expandNode(currentNode, goalNode, priorityQueue);
            }else{
                break;
            }
        }

        System.out.println("Moving on to second stage!");
    }

    private void expandNode(Node node, Node goalNode, ArrayList<Node> priorityQueue){

        System.out.println("Expanding: " + node);

        SortByPriority sortByPriority = new SortByPriority();

        node.setExpanded(true);

        for (Edge edge: node.getOutgoingEdgeList()) {

            if (edge.getToNode().getCost() > node.getCost() + edge.getTime()){
                edge.getToNode().setCost(node.getCost() + edge.getTime());
            }

            if (edge.getToNode().hasDirectdistanceCalculated()){
                edge.getToNode().setPriority((int)(edge.getToNode().getDirectDistance() / 1000 / 130 * 3600) + edge.getToNode().getCost());
            }else {
                edge.getToNode().calculateDirectDistanceToNode(goalNode);
                edge.getToNode().setPriority((int)(edge.getToNode().getDirectDistance() / 1000 / 130 * 3600) + edge.getToNode().getCost());
            }

            if (!edge.getToNode().isExpanded()){
                priorityQueue.add(edge.getToNode());
                priorityQueue.sort(sortByPriority);
            }
        }
    }

    public static void main(String[] args) throws Exception{

        MapGraph mapGraph = new MapGraph();
        Loader loader = new Loader();

        Node[] nodeArray = loader.loadNodes("noder.txt");

        Edge[] edgeArray = loader.loadEdges("kanter.txt", nodeArray);

        mapGraph.AStar(nodeArray, edgeArray, nodeArray[0], nodeArray[2]);
    }
}
