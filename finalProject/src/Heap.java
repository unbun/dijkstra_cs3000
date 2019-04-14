/*******************************************************************
 ******************* Minimum Heap Priority Queue *******************
 *******************************************************************/

/**
 * A Minimum Heap with each Node being weighted and prioritized by it's weight
 */
class Heap {

  int[] heap, key, pos;

  int allocatedSize, heapSize;

  public Heap(int size) {
    // To make life easier we index all the vertices from 1 to N,
    // and the heap also from 1 to N, so we need to allocate one more
    heap = new int[size + 1];
    key = new int[size + 1];
    pos = new int[size + 1];
    allocatedSize = size + 1;
    heapSize = size;
  }

  public int size() {
    return heapSize;
  }

  public int getKey(int v) {
    return key[v];
  }

  private void swap(int idx1, int idx2) {
    // Swapping the a-th element and the b-th element in the heap
    pos[heap[idx1]] = idx2;
    pos[heap[idx2]] = idx1;
    int tmp = heap[idx2];
    heap[idx2] = heap[idx1];
    heap[idx1] = tmp;
  }



  public void initHeap(int source) {
    for (int i = 1; i <= heapSize; i++) {
      // Put Vertex i as the i-th element in the heap
      heap[i] = i;
      pos[i] = i;
      key[i] = 100000000;
    }

    // Set the key of the source vertex and make it the root of the heap
    key[source] = 0;
    swap(1, source);
  }

  public int extractMin() {
    int res = heap[1];
    swap(1, heapSize);
    heapSize -= 1; // Remember to decrease the heap size before bubble down
    bubbleDown(1);
    return res;
  }

  public boolean decreaseKey(int u, int v, int w) {
    int newKey = key[u] + w;
    if (key[v] > newKey) {
      key[v] = newKey;
      bubbleUp(pos[v]);
      return true;
    }
    return false;
  }

  /**
   * Performs the "bubble down" operation to place the element that is at the
   * root of the heap in its correct place so that the heap maintains the
   * min-heap order property.
   */
  private void bubbleDown(int index) {

    // bubble down
    while (hasLeftChild(index)) {
      // which of my children is smaller?
      int smallerChild = leftIndex(index);

      // bubble with the smaller child, if I have a smaller child
      if (hasRightChild(index)
          && heap[leftIndex(index)] > heap[rightIndex(index)] {
        smallerChild = rightIndex(index);
      }

      if (heap[index] > heap[smallerChild]) {
        swap(index, smallerChild);
      } else {
        // otherwise, get outta here!
        break;
      }

      // make sure to update loop counter/index of where last el is put
      index = smallerChild;
    }
  }


  /**
   * Performs the "bubble up" operation to place a newly inserted element
   * (i.e. the element that is at the size index) in its correct place so
   * that the heap maintains the min-heap order property.
   */
  private void bubbleUp(int index) {

    while (hasParent(index)
        && (parent(index) > heap[index])) {
      // parent/child are out of order; swap them
      swap(index, parentIndex(index));
      index = parentIndex(index);
    }
  }

  private boolean hasParent(int i) {
    return i > 1;
  }


  private int leftIndex(int i) {
    return i * 2;
  }


  private int rightIndex(int i) {
    return i * 2 + 1;
  }


  private boolean hasLeftChild(int i) {
    return leftIndex(i) <= size();
  }


  private boolean hasRightChild(int i) {
    return rightIndex(i) <= size();
  }


  private int parent(int i) {
    return heap[parentIndex(i)];
  }


  private int parentIndex(int i) {
    return i / 2;
  }
}