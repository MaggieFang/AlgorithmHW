package com.hw4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author by Maggie Fang. Email menogenfong@gmail.com. Date on 11/19/18
 * Talk is Cheap,Show me the Code.
 **/
public class ShortestPath2 {
    static private int V;
    static private int source;
    static private int des;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        V = scanner.nextInt();
        int E = scanner.nextInt();
        int matrix[][] = new int[V][V];
        int count = 0;
        while (count < E) {
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            int v = scanner.nextInt();
            matrix[i][j] = v;
            matrix[j][i] = v;
            count++;
        }
        source = scanner.nextInt();
        des = scanner.nextInt();
        dijkstra(matrix, source);
    }

    @SuppressWarnings("unchecked")
    public static int selectMinDistanceVertex(int dist[], boolean used[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < V; v++)
            if (!used[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    @SuppressWarnings("unchecked")
    public static void dijkstra(int graph[][], int src) {

        int dist[] = new int[V]; //shortest distance for source to i
        ArrayList<Integer>[] path = new ArrayList[V];
        boolean used[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            path[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            used[i] = false;
        }

        dist[src] = 0;
        path[src].add(src);

        for (int count = 0; count < V - 1; count++) {
            int u = selectMinDistanceVertex(dist, used);
            used[u] = true;
            for (int v = 0; v < V; v++)
                if (!used[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] <= dist[v]) {
                    if (dist[u] + graph[u][v] == dist[v]) {
                        if (path[v].size() > path[u].size() + 1) {
                            dist[v] = dist[u] + graph[u][v];
                            path[v].clear();
                            path[v].addAll(path[u]);
                            path[v].add(v);
                        } else if (path[v].size() == path[u].size() + 1) {
                            for (int i = 0; i < path[u].size(); i++) {
                                if (path[u].get(i) < path[v].get(i)) {
                                    dist[v] = dist[u] + graph[u][v];
                                    path[v].clear();
                                    path[v].addAll(path[u]);
                                    path[v].add(v);
                                }else if(path[u].get(i) > path[v].get(i)){
                                    break;
                                }
                            }
                        }
                    } else {
                        dist[v] = dist[u] + graph[u][v];
                        path[v].clear();
                        path[v].addAll(path[u]);
                        path[v].add(v);

                    }
                }
        }

        System.out.println(dist[des]);
        for (int i = 0; i < path[des].size(); i++) {
            if (i != path[des].size() - 1) {
                System.out.print(path[des].get(i) + " ");
            } else {
                System.out.print(path[des].get(i));
            }
        }
        System.out.println();
    }


}
