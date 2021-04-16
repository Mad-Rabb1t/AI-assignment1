package io;

import entity.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Input {
    private final HashMap<Integer, ArrayList<Vertex>> mapOfEdgesFromVertices;
    private final HashMap<Integer, Integer> mapOfVertices;
    private int s;
    private int d;

    public Input(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        mapOfEdgesFromVertices = new HashMap<>();
        mapOfVertices = new HashMap<>();
        while (sc.hasNextLine()) {
            String trimmed = sc.nextLine().trim();
            if (!trimmed.isEmpty() && !trimmed.contains("#")) {
                String[] vars = trimmed.split(",");
                if (vars.length == 3) {
                    int from = Integer.parseInt(vars[0].trim());
                    int to = Integer.parseInt(vars[1].trim());
                    double dist = Double.parseDouble(vars[2].trim()) / 100;
                    addToEdges(from, new Vertex(to, mapOfVertices.get(to), dist));
                    addToEdges(to, new Vertex(from, mapOfVertices.get(from), dist));
                } else {
                    if (vars[0].trim().equals("S")) s = Integer.parseInt(vars[1].trim());
                    else if (vars[0].trim().equals("D")) d = Integer.parseInt(vars[1].trim());
                    else mapOfVertices.put(Integer.parseInt(vars[0].trim()),
                                Integer.parseInt(vars[1].trim()));
                }
            }
        }
    }

    private void addToEdges(int from, Vertex v) {
        mapOfEdgesFromVertices.computeIfAbsent(from, (f -> new ArrayList<>())).add(v);
    }

    public HashMap<Integer, ArrayList<Vertex>> getMapOfEdges() {
        return mapOfEdgesFromVertices;
    }

    public HashMap<Integer, Integer> getMapOfVertices() {
        return mapOfVertices;
    }

    public int getSource() {
        return s;
    }

    public int getDestination() {
        return d;
    }
}
