public class MapGraph {

    public void AStar(Node[] nodeArray, Edge[] edgeArray, Node startingNode, Node goalNode){



    }



    public static void main(String[] args) throws Exception{

        MapGraph mapGraph = new MapGraph();
        Loader loader = new Loader();

        Node[] nodeArray = loader.loadNodes("noder.txt");

        Edge[] edgeArray = loader.loadEdges("kanter.txt", nodeArray);

        mapGraph.AStar(nodeArray, edgeArray, nodeArray[0], nodeArray[1]);
    }
}
