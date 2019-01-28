import java.util.*;
import java.io.*;
import java.lang.*;

// find the shortest path from a source vertex to 
// all the other vertices in the graph
class Dijkstra {

    // initialize the starting vertex with a value of 0
    // and all other vertices with INFINITY
    // numV - the total number of vertices
    // startV - the starting vertex
    private static void initialize(int numV, int startV, int[] shPath, boolean[] finalV){

        for (int i = 0; i < numV; ++i){
            shPath[i] = Integer.MAX_VALUE;
            finalV[i] = false;
        }

        // the starting vertex
        shPath[startV] = 0;
        finalV[startV] = true;
    }


    // checks if shortest path of all the nodes are calculated from source
    // ie. true if all values of finalV are true
    // false otherwise
    private static boolean isComplete(int numV, boolean[] finalV){
        
        for (int i = 0; i < numV; ++i){
            if (finalV[i] == false)
                return false;
        }
        return true;
    }

    // returns the node with the shortest path from source to select next
    private static int retMinNode(int numV, int[] shPath, boolean[] finalV){
        
        int minNode = -1;
        int minPath = Integer.MAX_VALUE;

        for (int i = 0; i < numV; ++i){
            if (finalV[i] == false && shPath[i] < minPath){
                minNode = i;
                minPath = shPath[i];
            }
        }

        return minNode;
    }

    // run the Dijkstra's algorithm over the graph represented as `dist`
    // store the resulting shortest path in the vector `shPath`
    private static void run(int numV, int startV, int[] shPath, int[][]  dist, boolean[] finalV){
        
        int wrkngNode = startV;

        while (isComplete(numV, finalV) == false){

            for (int adjNode = 0; adjNode < numV; ++adjNode){
                if (dist[wrkngNode][adjNode] != 0 && (shPath[wrkngNode] + dist[wrkngNode][adjNode]) < shPath[adjNode]){
                    
                    // update the shortest path upto the adjascent node
                    shPath[adjNode] = shPath[wrkngNode] + dist[wrkngNode][adjNode];
                }
            }

            // find the node with minimum path from source which hasn't been finalized yet
            wrkngNode = retMinNode(numV, shPath, finalV);
            finalV[wrkngNode] = true;
        }

    }

    // print the shortest path from startV
    private static void printShortestPath(int numV, int startV, int[] shPath){
        
        for (int i = 0; i < numV; ++i){
            System.out.println("Distance from " + startV + " to " + i + " is " + shPath[i]);
        }
    }

    public static void main(String[] args){

        // number of vertices - vertices are represented as integers from 0 to 8
        int numV = 9;

        // the shortest path distance between vertices
        int[] shPath = new int[numV];

        // the starting vertex
        int startV = 0;

        // finalized
        boolean[] finalV = new boolean[numV];
        
        // the cost between the adjascent vertices
        int[][] dist = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                                {0, 0, 2, 0, 0, 0, 6, 7, 0}
                            };               
        

        Dijkstra.initialize(numV, startV, shPath, finalV);
        Dijkstra.run(numV, startV, shPath, dist, finalV);
        Dijkstra.printShortestPath(numV, startV, shPath);
    }
}