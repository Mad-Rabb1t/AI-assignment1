package solution;

import entity.Vertex;
import io.Input;

public class Astar extends UCS {
    private final int destSquareId;

    public Astar(Input input) {
        super(input);
        destSquareId = input.getMapOfVertices().get(input.getDestination());
    }

    @Override
    double totalCost(Vertex vertex) {
        int abs = Math.abs(vertex.getSquareID() - destSquareId);
        int height = (abs / 10) * 10;
        int width = (abs % 10) * 10;
        double heuristic = Math.sqrt(height * height + width * width);
        return heuristic + super.totalCost(vertex);
    }
}
