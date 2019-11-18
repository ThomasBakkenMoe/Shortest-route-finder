public class Edge {
    private Node fromNode;
    private Node toNode;
    private Edge opposingEdge;
    private int cost = 0;

    public Edge(Node fromNode, Node toNode, int cost){
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.cost = cost;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}