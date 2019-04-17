import java.util.Scanner;

// A Parsing class to be able to get a graph and any other parameters from the Scanner.
class Parser {

  Scanner sc;
  Graph graph;
  int uSrc, uDest, nV, nE;

  public Parser() {
    this.sc = new Scanner(System.in);
    this.graph = null;
    this.uSrc = -1;
    this.uDest = -1;
    this.nV = -1;
    this.nE = -1;
  }

  public void generate() {
    this.nV = sc.nextInt();
    this.nE = sc.nextInt();

    Graph g = new Graph(nV);

    for (int i = 0; i < nE; i++) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      int cost = sc.nextInt();
      g.addEdge(from, to, cost);
    }

    this.graph = g;

    this.uSrc = sc.nextInt();
    this.uDest = sc.nextInt();
  }

  // generate a test graph to test everything but parsing
  public void generateDummy(int source, int dest) {
    int V = 5;
    Graph g = new Graph(V);
    g.addEdge(1, 2, 10);
    g.addEdge(1, 5, 100);
    g.addEdge(1, 4, 30);
    g.addEdge(2, 3, 50);
    g.addEdge(3, 5, 10);
    g.addEdge(4, 3, 20);
    g.addEdge(4, 5, 60);
    this.graph = g;
    this.nV = 5;
    this.nE = 7;
    this.uSrc = source;
    this.uDest = dest;
  }

  /**
   * GETTERS
   **/
  public Graph getGraph() {
    return graph;
  }

  public int getSource() {
    return uSrc;
  }

  public int getDest() {
    return uDest;
  }
}
