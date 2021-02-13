package entity;

public class Edge {
    public final int from;
    public final int to;
    public final double distance;

    public Edge(int from, int to, double distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
}
