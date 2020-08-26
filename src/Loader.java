import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Class for loading edges and nodes from files into arrays.
 */
public class Loader {

    public Node[] loadNodes(String filename) throws Exception{
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int numberOfNodes = Integer.parseInt(bufferedReader.readLine().replaceAll("\\s", ""));

        Node[] nodeArray = new Node[numberOfNodes];

        String currentLine = "";
        Node newNode;

        for (int i = 0; i < numberOfNodes; i++) {
            currentLine = bufferedReader.readLine();
            String[] currentLineArray = currentLine.trim().split("\\s+");
            newNode = new Node(Integer.parseInt(currentLineArray[0]));
            newNode.setLatitude(Double.parseDouble(currentLineArray[1]));
            newNode.setLongitude(Double.parseDouble(currentLineArray[2]));

            nodeArray[i] = newNode;
        }

        bufferedReader.close();
        fileReader.close();

        return nodeArray;
    }

    public Edge[] loadEdges(String filename, Node[] nodeArray) throws Exception{
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int numberOfEdges = Integer.parseInt(bufferedReader.readLine().replaceAll("\\s", ""));

        Edge[] edgeArray = new Edge[numberOfEdges];

        String currentLine = "";
        Edge newEdge;

        for (int i = 0; i < numberOfEdges; i++) {
            currentLine = bufferedReader.readLine();
            String[] currentLineArray = currentLine.trim().split("\\s+");
            newEdge = new Edge(nodeArray[Integer.parseInt(currentLineArray[0])], nodeArray[Integer.parseInt(currentLineArray[1])], Integer.parseInt(currentLineArray[2]), Integer.parseInt(currentLineArray[3]), Integer.parseInt(currentLineArray[4]));

            newEdge.getFromNode().getOutgoingEdgeList().add(newEdge);
            edgeArray[i] = newEdge;
        }

        bufferedReader.close();
        fileReader.close();

        return edgeArray;
    }
}