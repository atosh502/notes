package com.atosh502;

import java.util.*;
import java.io.*;

public class dcp292_bipartite_matching {

    // assign the vertices with two colors if possible
    static boolean solve_bipartite_matching(int[][] graph, int V,  int[] color){

        Queue<Integer> queue = new LinkedList<>();

        int root = 0;    // the source vertex
        color[root] = 0;   // assign a color to the source vertex i.e. color[root] = 0 aka RED
        queue.add(root);

        while (queue.size() > 0){

            int src = queue.remove();

            // find all the adjacent verties of the given source vertex
            for (int adj = 0; adj < V; ++adj){

                if (graph[src][adj] == 1){

                    // if color is already assigned to the adjacent vertex; and it equals to that of source
                    // no bipartite matching possible

                    if (color[adj] != -1 && color[adj] == color[src]){
                        return false;
                    }


                    // if no color is assigned
                    if (color[adj] == -1){
                        queue.add(adj);
                        color[adj] = (color[src] + 1) % 2;
                    }

                }
            }
        }

        // if Murphy's law fails
        return true;
    }

    static void read_graph(List<List<Integer>> gph){

        Scanner cin = new Scanner(System.in);

        // read the number of vertices
        int V = cin.nextInt();

        cin.nextLine();
        // for each vertex read the adjacent vertices
        for (int i = 0; i < V; ++i){

            List<Integer> vertices = new ArrayList<>();

            for (String vertex : cin.nextLine().split(" ")){

                if (vertex != " " && vertex != ""){
                    vertices.add(Integer.parseInt(vertex));
                }
            }

            gph.add(vertices);
        }
    }

    static void convert_to_matrix(List<List<Integer>> graph_adj, int[][] graph, int V){

        for (int i = 0; i < V; ++i){
            for (int j = 0; j < V; ++j){
                graph[i][j] = 0;
            }
        }

        for (int src = 0; src < V; ++src){
            for (Integer vertex : graph_adj.get(src)){
                graph[src][vertex] = 1;
            }
        }
    }

    public static void main(String[] args){

        // adjacency matrix representation
        // assuming no self loops
        // int[][] graph = {{0, 1, 0, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}};

        // example graph input and output
//        6
//        3
//        2
//        1 4
//        0 4 5
//        2 3
//        3
//        Red team: [0, 1, 4, 5]
//        Blue team: [2, 3]

        List<List<Integer>> graph_adj = new ArrayList<>();
        read_graph(graph_adj);

        int V = graph_adj.size();
        int[][] graph = new int[V][V];
        convert_to_matrix(graph_adj, graph, V);

        // vertices are numbered from 0.. so color[0] indicates the color for vertex 0 and so on..
        int[] color = new int[V];       // the array holding the color of vertices

        for (int i = 0; i < V; ++i){
            color[i] = -1;      // no color assigned.. 0 means RED, and 1 means BLUE
        }

        if (solve_bipartite_matching(graph, V, color)){

            // print the solution
            List<Integer> red = new LinkedList<>();
            List<Integer> blue = new LinkedList<>();

            for (int i = 0; i < V; ++i){
                if (color[i] == 0) {
                    red.add(i);
                } else {
                    blue.add(i);
                }
            }

            System.out.println("Red team: " + red.toString());
            System.out.println("Blue team: " + blue.toString());

        } else {
            System.out.println("Not possible!");
        }

    }
}
