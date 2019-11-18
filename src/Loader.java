import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Loader {

    public void loadNodesAndEdgesFromFile(String filenameNodes, String filenameEdges, ArrayList<Node> nodeList, ArrayList<Edge> edgeList) throws Exception {
        loadNodes(filenameNodes, nodeList);
        loadEdges(filenameEdges, nodeList, edgeList);
    }

    private void loadNodes(String filename, ArrayList<Node> nodeList) throws Exception{
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int numberOfNodes = Integer.parseInt(bufferedReader.readLine().replaceAll("\\s", ""));

        String currentLine = "";
        Node newNode;

        for (int i = 0; i < numberOfNodes; i++) {
            currentLine = bufferedReader.readLine();
            String[] currentLineArray = currentLine.trim().split("\\s+");
            newNode = new Node(Integer.parseInt(currentLineArray[0]));
            newNode.setLatitude(Double.parseDouble(currentLineArray[1]));
            newNode.setLongitude(Double.parseDouble(currentLineArray[2]));

            nodeList.add(i, newNode);
            //System.out.println("Loaded node: " + newNode);
        }
    }

    private void loadEdges(String filename, ArrayList<Node> nodeList, ArrayList<Edge> edgeList) throws Exception{
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int numberOfEdges = Integer.parseInt(bufferedReader.readLine().replaceAll("\\s", ""));

        String currentLine = "";
        Edge newEdge;

        for (int i = 0; i < numberOfEdges; i++) {
            currentLine = bufferedReader.readLine();
            String[] currentLineArray = currentLine.trim().split("\\s+");
            newEdge = new Edge(nodeList.get(Integer.parseInt(currentLineArray[0])), nodeList.get(Integer.parseInt(currentLineArray[1])), Integer.parseInt(currentLineArray[2]) * 100, Integer.parseInt(currentLineArray[3]), Integer.parseInt(currentLineArray[4]));

            edgeList.add(i, newEdge);
            //System.out.println("Loaded edge: " + edgeList);
        }
    }
}