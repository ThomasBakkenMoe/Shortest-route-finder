import java.util.ArrayList;

public class Node {

    private static final int r = 6371; // Radius of Earth (in km)
    private int nodeNum;
    private int distance = 1000000000; // The initial value is to be considered infinite
    private Node previousNode;
    private ArrayList<Edge> outgoingEdgeList = new ArrayList<>();
    private boolean discovered = false;
    private boolean temporaryFlag = false;

    private double latitude = 0.0;
    private double longitude = 0.0;

    public Node(int nodeNum){
        this.nodeNum = nodeNum;
    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof Node && ((Node) obj).nodeNum == this.nodeNum;

    }

    @Override
    public String toString() {
        return "" + nodeNum;
    }

    public int getLength() {
        return distance;
    }

    public void setLength(int length) {
        this.distance = length;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public int getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(int nodeNum) {
        this.nodeNum = nodeNum;
    }

    public ArrayList<Edge> getOutgoingEdgeList() {
        return outgoingEdgeList;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    public boolean hasTemporaryFlag() {
        return temporaryFlag;
    }

    public void setTemporaryFlag(boolean temporaryFlag) {
        this.temporaryFlag = temporaryFlag;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double calculateDirectDistanceToNode(Node node){

        // Method for calculating the distance between two points along the surface of a sphere (like the Earth)

        return 2 * r * Math.asin(Math.sqrt(Math.sin((latitude - node.getLatitude()) / 2) + Math.cos(latitude) * Math.cos(node.getLatitude()) * Math.pow(Math.sin((longitude - node.getLongitude()) / 2), 2)));
    }
}
