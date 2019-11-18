import java.util.ArrayList;

public class Node {

    private static final int r = 6371; // Radius of Earth (in km)
    private int nodeNum;
    private int cost = 1000000000; // The initial value is to be considered infinite
    private int priority = 1000000000; // The initial value is to be considered infinite
    private double directDistance = 0.0;
    private Node previousNode;
    private ArrayList<Edge> outgoingEdgeList = new ArrayList<>();
    private boolean expanded = false;
    private boolean directdistanceCalculated = false;

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

    public int getCost() {
        return cost;
    }

    public void setCost(int length) {
        this.cost = length;
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

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
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

        directdistanceCalculated = true;

        setDirectDistance(2 * r * Math.asin(Math.sqrt(Math.sin((latitude - node.getLatitude()) / 2) + Math.cos(latitude) * Math.cos(node.getLatitude()) * Math.pow(Math.sin((longitude - node.getLongitude()) / 2), 2))));

        return directDistance;
    }

    public boolean hasDirectdistanceCalculated() {
        return directdistanceCalculated;
    }

    public double getDirectDistance() {
        return directDistance;
    }

    public void setDirectDistance(double directDistance) {
        this.directDistance = directDistance;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
