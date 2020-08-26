import java.util.ArrayList;

/**
 * Class representing a Node
 * The same node class is used in both Dijkstra and A*,
 * and it contains the methods to calculate the distance to other nodes.
 */
public class Node implements Comparable{

    private static final int r = 6371; // Radius of Earth (in km)
    private int nodeNum;
    private int cost = 1000000000; // The initial value is to be considered infinite
    private int priority = 1000000000; // The initial value is to be considered infinite
    private double directDistance = 0.0;
    private Node previousNode;
    private ArrayList<Edge> outgoingEdgeList = new ArrayList<>();
    private boolean expanded = false;
    private boolean discovered = false;
    private boolean directdistanceCalculated = false;

    private double latitude = 0.0;
    private double longitude = 0.0;

    public Node(int nodeNum){
        this.nodeNum = nodeNum;
    }

    /**
     * Method for calculating the distance between two points along the surface of a sphere (like the Earth)
     * This method is used by A* to calculate the direct distance to the goal node, and thus setting the priority
     * of expanding this node.
     *
     * @param node the node that we want to calculate the distance to.
     * @return The calculated direct distance to the given node.
     */
    public double calculateDirectDistanceToNode(Node node){

        // Method for calculating the distance between two points along the surface of a sphere (like the Earth)

        directdistanceCalculated = true;

        setDirectDistance(2 * r * Math.asin(Math.sqrt(
                Math.sin(Math.toRadians((latitude - node.getLatitude()) / 2)) * Math.sin(Math.toRadians((latitude - node.getLatitude()) / 2)) +
                        Math.cos(Math.toRadians(latitude)) *
                                Math.cos(Math.toRadians(node.getLatitude())) *
                                Math.sin(Math.toRadians((longitude - node.getLongitude()) / 2)) * Math.sin(Math.toRadians((longitude - node.getLongitude()) / 2)))));

        return directDistance;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int time) {
        this.cost = time;
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

    public boolean hasDirectdistanceCalculated() {
        return directdistanceCalculated;
    }

    public void setDirectdistanceCalculated(boolean directdistanceCalculated){
        this.directdistanceCalculated = directdistanceCalculated;
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

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    @Override
    public int compareTo(Object o) {

        Node otherNode;
        if (o instanceof Node){

            otherNode = (Node) o;
            return priority - otherNode.getPriority();
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof Node && ((Node) obj).nodeNum == this.nodeNum;

    }

    @Override
    public String toString() {
        return "" + nodeNum + " lat: " + latitude + " long: " + longitude + " Prev. node: " + previousNode;
    }
}
