/*******************************************************************
 ********************** Dijkstra Path Finding **********************
 *******************************************************************/

/**
 * Path finder to get the distance from any source node to every other node in a given adjacency
 * list.
 */
class Dijkstra {

  private int dist[]; //result array

  private int V; // Number of vertices

  private Integer[] settled;
  private int settledSize;
  private PriorityQueue pq;
  Node[][] adj;

  //constructor: give the |V| of the graph
  public Dijkstra(int V)
  {
    this.V = V;
    dist = new int[V];
    settled = new Integer[V];
    pq = new PriorityQueue(V);
  }

  // add to the settled array and also increase it's real count
  private void addToSettled(Integer i){
    settled[settledSize] = i;
    settledSize ++;
  }

  // check if settled contains the given Integer
  private boolean settledContains(Integer i){
    for(int idx = 0; idx < settledSize; idx++){
      if(settled[idx] == i){
        return true;
      }
    }
    return false;
  }

  // Call Dijkstra's algorithm.
  // EFFECT: stores all the distances from source to each node
  // (according to the algo and the given adjacency lists) in the class's dist[] array
  public void dijkstra(Node[][] adj, int source)
  {
    this.adj = adj;

    for (int i = 0; i < V; i++)
      dist[i] = Integer.MAX_VALUE;

    // Add source node to the priority queue
    pq.add(new Node(source, 0));

    // Distance to the source is 0
    dist[source] = 0;
    while (settledSize != V && pq.size() != 0) {

      // remove the minimum distance node
      // from the priority queue
      int u = pq.extractMin().id;

      // adding the node whose distance is
      // finalized
      addToSettled(u);

      e_Neighbours(u);
    }

    // Per prompt, invalid paths should be -1 but -1 is gonna come up as less than the min path,
    // so we had to use MAX_VALUE as the default values when running dijkstra. This
    for(int i = 0; i < V; i ++){
      if(dist[i] == Integer.MAX_VALUE){
        dist[i] = -1;
      }
    }
  }

  // Function to process all the neighbours
  // of the node with the given id.
  private void e_Neighbours(int u)
  {
    int edgeDistance = -1;
    int newDistance = -1;

    // All the neighbors of v
    for (int i = 0; i < adj[u].length; i++) {
      Node v = adj[u][i];

      // If current node hasn't already been processed
      if (!settledContains(v.id)) {
        edgeDistance = v.cost;
        newDistance = dist[u] + edgeDistance;

        // If new distance is cheaper in cost
        if(v.id != -1){
          if (newDistance < dist[v.id])
            dist[v.id] = newDistance;

          // Add the current node to the queue
          pq.add(new Node(v.id, dist[v.id]));
        }

      }
    }
  }

  //get the dists that are currently stored
  public int[] getDist() {
    return dist;
  }
}
