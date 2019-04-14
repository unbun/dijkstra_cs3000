/*******************************************************************
 ********************** Dijkstra Path Finding **********************
 *******************************************************************/

/**
 * Path finder to get the distance from any source node to every other node in a given adjacency
 * list.
 */
class Dijkstra {

  void addEdge(ConsList[] adjacencyList, int u, int v, int w) {
    adjacencyList[u] = new ConsList(v, w, adjacencyList[u]);
  }
}
