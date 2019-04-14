import java.util.Scanner;

public class Solution {

  private static Scanner sc;

  public static void main(String args[]) {

    sc = new Scanner(System.in);

    int nVertices = sc.nextInt();
    int nEdges = sc.nextInt();

    // adjust the vertices because I made Dijkstra use zero-indexed vertices, and the input will be
    // one-indexed. This could be easily solved by simply using HashMaps instead, but because I
    // can't this is essentially a trade of memory for time, where we lose memory for an adjacency
    // list for a Node 0, but the arrays will operate faster.
    int nvAdjusted = nVertices + 1;


    Node[][] adj = parseForAdjacencyLists(nvAdjusted, nEdges);

    int source = sc.nextInt();
    int dest = sc.nextInt();

    // Calculate the single source shortest path
    Dijkstra finder = new Dijkstra(nvAdjusted);
    finder.dijkstra(adj, source);

    System.out.println(finder.getDist()[dest]);
  }

  private static Node[][] parseForAdjacencyLists(int nV, int nE) {

    Node[][] result = new Node[nV][nV];
    for (int i = 0; i < nV; i++) {
      for (int j = 0; j < nV; j++) {
        result[i][j] = new NullNode();
      }
    }

    // stores which index we are at for each node's adjacency lists
    int[] counterPerFroms = new int[nV];
    for (int i = 0; i < nV; i++) {
      counterPerFroms[i] = 0;
    }

    int argIdx = 2;

    for (int i = 0; i < nE; i++) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      int cost = sc.nextInt();

      argIdx += 3;

      int fromIdx = counterPerFroms[from];
      result[from][fromIdx] = new Node(to, cost);
      counterPerFroms[from]++;
    }

    return result;
  }

}
