import java.util.Scanner;

class Dijkstra {

  private static Scanner sc;

  public static void main(String args[]) {
    Parser build = new Parser();

//    build.generateDummy(2, 1);
    build.generate();

    int minCost = dijkstra(build.getGraph(), build.getSource(), build.getDest());
    System.out.println(minCost);
  }

  // The main function that calculates distances of shortest paths from src to dest (really finds all
  // vertices' distances, but only returns for the dest.
  // It is a O(ELogV) function since we are using an adjacency list on Dijkstra
  static int dijkstra(Graph graph, int src, int dest) {
    int V = graph.nVerts;
    int[] dist = new int[V];

    // minHeap represents set edges
    MinHeap minHeap = new MinHeap(V);

    for (int v = 0; v < V; ++v) {
      dist[v] = Integer.MAX_VALUE;
      minHeap.heap[v] = new DVertex(v, dist[v]);
      minHeap.pos[v] = v;
    }

    // Make dist value of src vertex as 0 so that it is extracted first 
    minHeap.heap[src] = new DVertex(src, dist[src]);
    minHeap.pos[src] = src;
    dist[src] = 0;
    minHeap.decreaseKey(src, dist[src]);

    // Initially size of min heap is equal to nVerts
    minHeap.size = V;


    while (!minHeap.isEmpty()) {
      DVertex DVertex = minHeap.extractMin();
      int u = DVertex.v; // Store the extracted vertex number


      ConsLinkedList crawl = graph.array[u].head;
      while (crawl != null) {
        int v = crawl.data;

        if (minHeap.isInMinHeap(v) && dist[u] != Integer.MAX_VALUE && crawl.weight + dist[u] < dist[v]) {
          dist[v] = dist[u] + crawl.weight;
          minHeap.decreaseKey(v, dist[v]); // update distance value in min heap also
        }

        crawl = crawl.rest;
      }
    }

    // print the calculated shortest distances
    // printDistTable(dist, nVerts, src);

    return (dist[dest] == Integer.MAX_VALUE) ? -1 : dist[dest];
  }


  static void printDistTable(int dist[], int n, int s) {
    System.out.println("Vertex Distances from Source '" + s + "'");
    for (int i = 0; i < n; ++i) {
      System.out.println(String.format("%d\t\t%d", i, dist[i]));
    }
  }
}
