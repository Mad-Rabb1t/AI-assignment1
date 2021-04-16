package app;

import io.Input;
import solution.Astar;
import solution.UCS;

import java.io.FileNotFoundException;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
    Input input = new Input("p1_graph.txt");

    UCS uniformCostSearch = new UCS(input);
    uniformCostSearch.solve();
    System.out.println("UCS : ");
    uniformCostSearch.showSolution();
    System.out.println("Cost: " + uniformCostSearch.getActualPathCost());
    System.out.println("Number of visited vertices: " + uniformCostSearch.getNumOfVisitedNodes());
    System.out.println();

    Astar astar = new Astar(input);
    astar.solve();
    System.out.println("A* : ");
    astar.showSolution();
    System.out.println("Cost: " + astar.getActualPathCost());
    System.out.println("Number of visited vertices: " + astar.getNumOfVisitedNodes());

  }

}
