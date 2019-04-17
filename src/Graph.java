class ConsLinkedList {

  int data;
  int weight;
  ConsLinkedList rest;

  public ConsLinkedList(int data, int weight) {
    this.data = data;
    this.weight = weight;
    this.rest = null;
  }

  public ConsLinkedList(int data, int weight, ConsLinkedList rest) {
    this.data = data;
    this.weight = weight;
    this.rest = rest;
  }
}

class LinkedList {

  ConsLinkedList head;

  public LinkedList() {
    head = null;
  }

  void addNode(int d, int w) {
    head = new ConsLinkedList(d, w, head);
  }
}

class Graph {

  int nVerts;
  LinkedList[] array;

  public Graph(int V) {
    nVerts = V + 1; //increment for one-indexed inputs

    array = new LinkedList[nVerts];
    for (int i = 0; i < nVerts; i++) {
      array[i] = new LinkedList();
    }
  }

  void addEdge(int src, int dest, int weight) {
    array[src].addNode(dest, weight);

    //uncomment this line for undirected graphs
    //heap[data].addNode(src, weight);
  }
}
