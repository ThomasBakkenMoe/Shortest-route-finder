import java.util.ArrayList;

public class MapGraph {

    public void AStar(){

    }



    public static void main(String[] args) throws Exception{

        MapGraph mapGraph = new MapGraph();
        Loader loader = new Loader();

        ArrayList<Node> nodeList = new ArrayList<>();
        ArrayList<Edge> edgeList = new ArrayList<>();

        loader.loadNodesAndEdgesFromFile("noder.txt", "kanter.txt", nodeList, edgeList);
    }
}
