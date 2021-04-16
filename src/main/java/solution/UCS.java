package solution;

import entity.Vertex;
import io.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class UCS {

    private final HashMap<Integer, ArrayList<Vertex>> mapOfEdges;
    private final PriorityQueue<Vertex> queue;
    private final HashMap<Integer, Integer> previousVertices;
    private final HashMap<Integer, Double> bestPaths;
    private final HashMap<Integer, Integer> mapOfVertices;
    private final ArrayList<Integer> shortestPath;
    private double actualPathCost;
    private int numOfVisitedNodes = 0;
    private final int S;
    private final int D;

    public UCS(Input input) {
        this.mapOfEdges = input.getMapOfEdges();
        this.mapOfVertices = input.getMapOfVertices();
        this.S = input.getSource();
        this.D = input.getDestination();
        this.bestPaths = new HashMap<>();
        this.previousVertices = new HashMap<>();
        this.shortestPath = new ArrayList<>();
        this.queue = new PriorityQueue<>(new VertexComparator() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                return Double.compare(totalCost(v1), totalCost(v2));
            }
        });
    }

    public void solve() {
        Vertex startVertex = new Vertex(S, mapOfVertices.get(S), 0);
        queue.add(startVertex);
        bestPaths.put(startVertex.getVertexID(), totalCost(startVertex));

        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.poll();

            if (bestPaths.getOrDefault(currentVertex.getVertexID(), Double.MAX_VALUE) < totalCost(currentVertex))
                continue;
            numOfVisitedNodes++;

            if (currentVertex.getVertexID() == D) {
                int vertexID = D;
                while (vertexID != S) {
                    shortestPath.add(vertexID);
                    vertexID = previousVertices.get(vertexID);
                }
                shortestPath.add(S);
                actualPathCost = currentVertex.getDistanceFromSource();
                break;
            }

            for (int i = 0; i < mapOfEdges.getOrDefault(currentVertex.getVertexID(), new ArrayList<>()).size(); i++) {
                Vertex neighborVertex = mapOfEdges.get(currentVertex.getVertexID()).get(i);
                if (bestPaths.getOrDefault(neighborVertex.getVertexID(), Double.MAX_VALUE) > currentVertex.getDistanceFromSource() + totalCost(neighborVertex)) {
                    bestPaths.put(neighborVertex.getVertexID(), currentVertex.getDistanceFromSource() + totalCost(neighborVertex));
                    Vertex adj2 = new Vertex(neighborVertex);
                    adj2.setDistanceFromSource(currentVertex.getDistanceFromSource() + neighborVertex.getDistanceFromSource());
                    queue.add(adj2);
                    previousVertices.put(neighborVertex.getVertexID(), currentVertex.getVertexID());
                }
            }
        }
    }

    double totalCost(Vertex vertex) {
        return vertex.getDistanceFromSource();
    }

    public double getActualPathCost() {
        return actualPathCost;
    }

    public int getNumOfVisitedNodes() {
        return numOfVisitedNodes;
    }

    public void showSolution() {
        System.out.print("Solution: ");
        shortestPath.forEach(v -> System.out.print(v + " <- "));
        System.out.println();
    }
}
