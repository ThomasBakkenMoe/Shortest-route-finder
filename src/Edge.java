
/**
 * Class representing an edge between two nodes. We can look at this as a stretch of road.
 * Contains information on how much time it takes to travel along the road, how long the road is, and how fast we can travel.
 * The opposingEdge is the same road, traveling the opposite way.
 * In this program's universe; all roads are one way, there just happens to be an opposing one-way road everywhere.
 */
public class Edge {
    private Node fromNode;
    private Node toNode;
    private Edge opposingEdge;
    private int time = 0; // Time in seconds
    private int length = 0; // Length in meters
    private int speed = 0; // Speed in km/h

    public Edge(Node fromNode, Node toNode, int time, int length, int speed){
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.time = time;
        this.length = length;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Edge from: " + fromNode + " to: " + toNode;
    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof Edge && ((Edge) obj).fromNode == this.fromNode && ((Edge) obj).toNode == this.toNode;

    }

    public Node getFromNode(){
        return fromNode;
    }

    public void setFromNode(Node fromNode) {
        this.fromNode = fromNode;
    }

    public Node getToNode() {
        return toNode;
    }

    public void setToNode(Node toNode) {
        this.toNode = toNode;
    }

    public Edge getOpposingEdge() {
        return opposingEdge;
    }

    public void setOpposingEdge(Edge opposingEdge) {
        this.opposingEdge = opposingEdge;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}