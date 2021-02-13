package app;

import entity.Edge;
import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;
import es.usc.citius.hipster.model.impl.WeightedNode;
import es.usc.citius.hipster.model.problem.SearchProblem;
import io.Input;

import java.io.FileNotFoundException;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
    Input input = new Input("p1_graph.txt");

    GraphBuilder<Integer, Double> graphBuilder = GraphBuilder.create();
    for (Edge e :
            input.getListOfEdges()) {
      graphBuilder.connect(e.from).to(e.to).withEdge(e.distance);
    }

    HipsterDirectedGraph<Integer, Double> graph = graphBuilder.createDirectedGraph();

    SearchProblem<Double, Integer, WeightedNode<Double, Integer, Double>> p = GraphSearchProblem.startingFrom(input.getSource())
            .in(graph).takeCostsFromEdges().build();


    System.out.println("Search results with Dijkstra: \n" + Hipster.createDijkstra(p).search(input.getDestination()));
    System.out.println("Search results with A*: \n" + Hipster.createAStar(p).search(input.getDestination()));
  }

}
