// A Vertex that will be Heaped for Dijkstra's
class DVertex {

  int v;
  int dist; //last known Dijkstra distance value

  public DVertex(int v, int dist) {
    this.v = v;
    this.dist = dist;
  }
}

// A Minimum Heap of DVertices
class MinHeap {

  int size, capacity;
  int pos[];
  DVertex heap[];

  public MinHeap(int capacity) {
    this.capacity = capacity + 1; // increment for one-indexed inputs
    this.pos = new int[capacity];
    this.size = 0;
    this.heap = new DVertex[capacity];
  }

  //swap the vertices at the given indices
  void swap(int idx1, int idx2) {
    DVertex temp = heap[idx1];
    heap[idx1] = heap[idx2];
    heap[idx2] = temp;
  }

  // The presented function to heapify at given idx, runs at O(log_2(|nVerts|))
  void minHeapify(int idx) {
    int smallest, left, right;
    smallest = idx;
    left = 2 * idx + 1;
    right = 2 * idx + 2;

    if (left < size &&
        heap[left].dist < heap[smallest].dist) {
      smallest = left;
    }

    if (right < size &&
        heap[right].dist < heap[smallest].dist) {
      smallest = right;
    }

    if (smallest != idx) {
      // The nodes to be swapped in min heap
      DVertex smallestNode = heap[smallest];
      DVertex idxNode = heap[idx];

      // Swap positions
      this.pos[smallestNode.v] = idx;
      this.pos[idxNode.v] = smallest;

      // Swap nodes
      swap(smallest, idx);

      minHeapify(smallest);
    }
  }

  boolean isEmpty() {
    return size == 0;
  }

  // return the current minimum and heapify it to ensure its the root
  DVertex extractMin() {
    if (isEmpty()) {
      return null;
    }

    // Store the root node
    DVertex root = heap[0];

    // Replace root node with last node
    DVertex lastNode = heap[size - 1];
    heap[0] = lastNode;

    // Update position of last node
    pos[root.v] = size - 1;
    pos[lastNode.v] = 0;

    // Reduce heap size and heapify root
    size--;
    minHeapify(0);

    return root;
  }

  // Decrease dist value of a given vertex and update pos[] so we can
  // get the current index of a node in min heap for path-finding. Runs at O(log(n))
  void decreaseKey(int v, int dist) {
    // Get the index of v in heap heap
    int i = pos[v];

    // Get the node and update its dist value
    heap[i].dist = dist;

    // Travel up while the complete tree is not heapified.
    while (i != 0 && heap[i].dist < heap[(i - 1) / 2].dist) {
      // Swap this node with its parent
      pos[heap[i].v] = (i - 1) / 2;
      pos[heap[(i - 1) / 2].v] = i;
      swap(i, (i - 1) / 2);

      // move to parent index
      i = (i - 1) / 2;
    }
  }

  // if the vertex with a value of v in the mean according to the pos[] array
  boolean isInMinHeap(int v) {
    if (pos[v] < size) {
      return true;
    }
    return false;
  }


}




