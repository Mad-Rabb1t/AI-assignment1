package io;

import entity.Edge;
import entity.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    private final List<Edge> listOfEdges;
    private final List<Vertex> listOfVertices;
    private int s;
    private int d;

    public Input(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        listOfEdges = new ArrayList<>();
        listOfVertices = new ArrayList<>();
        while (sc.hasNextLine()) {
            String trimmed = sc.nextLine().trim();
            if (!trimmed.isEmpty() && !trimmed.contains("#")) {
                String[] vars = trimmed.split(",");
                if (vars.length == 3) {
                    listOfEdges.add(
                            new Edge(Integer.parseInt(vars[0].trim()), Integer.parseInt(vars[1].trim()), Integer.parseInt(vars[2].trim()))
                    );
                }
                else {
                    if (vars[0].trim().equals("S")) s = Integer.parseInt(vars[1].trim());
                    else if (vars[0].trim().equals("D")) d = Integer.parseInt(vars[1].trim());
                    else listOfVertices.add(new Vertex(Integer.parseInt(vars[0].trim()),
                                Integer.parseInt(vars[1].trim())));
                }
            }
        }
    }

    public List<Edge> getListOfEdges() {
        return listOfEdges;
    }

    public List<Vertex> getListOfVertices() {
        return listOfVertices;
    }

    public int getSource() {
        return s;
    }

    public int getDestination() {
        return d;
    }
}
