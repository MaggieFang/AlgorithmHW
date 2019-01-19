package com.hw4;

import java.util.Scanner;

/**
 * Author by Maggie Fang. Email menogenfong@gmail.com. Date on 11/19/18
 * Talk is Cheap,Show me the Code.
 **/
public class ShortestPath {
    private static final int NO_PARENT = -1;
    static private int V;
    static private int source;
    static private int des;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        V = scanner.nextInt();
        int E = scanner.nextInt();
        int matrix[][] = new int[V][V];
        int total = E;
        int count = 0;
        while (count < total) {
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            int v = scanner.nextInt();
            matrix[i][j] = v;
            matrix[j][i] = v;
            count++;
        }
        source = scanner.nextInt();
        des = scanner.nextInt();
        ShortestPath test = new ShortestPath();
        test.dijkstra(matrix, source);
    }

    int minDistance(int dist[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    private static void printPath(int currentVertex,
                                  int[] parents) {

        if (currentVertex == NO_PARENT) {
            return;
        }
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
    }

    void dijkstra(int graph[][], int src) {
        int dist[] = new int[V]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        Boolean sptSet[] = new Boolean[V];
        int[] parents = new int[V];
        int[] parentLayer = new int[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;
        parents[src] = NO_PARENT;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] <= dist[v]) {
                    if (dist[u] + graph[u][v] == dist[v]) {
                        if (parentLayer[v] > parentLayer[u] + 1) {
                            dist[v] = dist[u] + graph[u][v];
                            parents[v] = u;
                            parentLayer[v] = parentLayer[u] + 1;
                        }else if(parentLayer[v] > parentLayer[u] + 1){}
                    } else {
                        dist[v] = dist[u] + graph[u][v];
                        parents[v] = u;
                        parentLayer[v] = parentLayer[u] + 1;

                    }


                }
        }

        // print the constructed distance array
        System.out.println(dist[des]);
        printPath(des, parents);
    }


}
