import java.util.Scanner;

class Solution {

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
    Node[][] adjSized = shrinkLists(adj);

    print2dNode(adj);
    System.out.println("***************************");
    print2dNode(adjSized);

    int source = sc.nextInt();
    int dest = sc.nextInt();

    // Calculate the single source shortest path
    Dijkstra finder = new Dijkstra(nvAdjusted);
    finder.dijkstra(adjSized, source);

    System.out.println(finder.getDist()[dest]);
  }

  //parse the scanner to generate the adjacency lists.
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

  //Shrink the list to make it more optimal for dijkstra
  private static Node[][] shrinkLists(Node[][] toShrink){
    int deepestNonZero = -1;

    for(int i = 0; i < toShrink.length; i++){
      for(int j = 0; j < toShrink[i].length; j++){
        if (toShrink[i][j].id != -1 && j > deepestNonZero){
          deepestNonZero = j;
        }
      }
    }

    System.out.println("\n\n" + deepestNonZero + "\n");

    Node[][] shrunk = new Node[toShrink.length][deepestNonZero + 1];
    for (int i =0; i < shrunk.length; i++){
      for(int j = 0; j < shrunk[i].length; j++){
        shrunk[i][j] = toShrink[i][j];
      }
    }

    return shrunk;
  }

  //print a 2D array of Nodes
  private static void print2dNode(Node[][] toPrint){
    for(Node[] nL : toPrint){
      for(Node n : nL){
        System.out.print(n.toString() + ",");
      }
      System.out.println();
    }
  }

}
