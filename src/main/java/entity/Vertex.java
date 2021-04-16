package entity;

import java.util.Objects;

public class Vertex {
    private final int vertexId;
    private final int squareId;
    private double distanceFromSource;

    public Vertex(int vertexID, int squareID, double distanceFromSource) {
        this.vertexId = vertexID;
        this.squareId = squareID;
        this.distanceFromSource = distanceFromSource;
    }

    public Vertex(Vertex x) {
        this.vertexId = x.vertexId;
        this.squareId = x.squareId;
        this.distanceFromSource = x.distanceFromSource;
    }

    public int getVertexID() {
        return vertexId;
    }

    public int getSquareID() {
        return squareId;
    }

    public double getDistanceFromSource() {
        return distanceFromSource;
    }

    public void setDistanceFromSource(double distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return vertexId == vertex.vertexId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexId);
    }
}