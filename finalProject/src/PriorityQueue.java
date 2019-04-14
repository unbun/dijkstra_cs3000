/*******************************************************************
 ******************* Minimum Heap Priority Queue *******************
 *******************************************************************/

/**
 * A Minimum Heap with each Node being weighted and prioritized by it's weight
 */
class PriorityQueue
{
  // array to store heap elements
  private Node[] A;

  // constructor: uses given initial capacity of array
  public PriorityQueue(int capacity)
  {
    A = new Node[capacity];
    for(int i = 0; i < capacity; i++){
      A[i] = new Node(-1, -1);
    }
  }

  // return parent of A[i]
  private int parent(int i)
  {
    // if i is already a root id
    if (i == 0)
      return 0;

    return (i - 1) / 2;
  }

  // return left child of A[i]
  private int leftChild(int i)
  {
    return (2 * i + 1);
  }

  // return right child of A[i]
  private int rightChild(int i)
  {
    return (2 * i + 2);
  }

  // swap values at two indexes
  void swap(int x, int y)
  {
    // swap with child having greater value
    Node temp = A[x];
    A[x] = A[y];
    A[y] = temp;
  }

  // Recursive Heapify-down procedure. A parent i, swap when
  // one of its two direct children violates the heap property
  private void heapify_down(int i)
  {
    // get left and right child of id at index i
    int left = leftChild(i);
    int right = rightChild(i);

    int smallest = i;

    // compare A[i] with its left and right child
    // and find smallest value
    if (left < size() && A[left].value() < A[i].value())
      smallest = left;

    if (right < size() && A[right].value() < A[smallest].value())
      smallest = right;

    if (smallest != i)
    {
      // swap with child having lesser value
      swap(i, smallest);

      // call heapify-down on the child
      heapify_down(smallest);
    }
  }

  // Recursive Heapify-up procedure
  private void heapify_up(int i)
  {
    // check if id at index i and its parent violates
    // the heap property
    if (i > 0 && A[parent(i)].value() > A[i].value())
    {
      // swap the two if heap property is violated
      swap(i, parent(i));

      // call Heapify-up on the parent
      heapify_up(parent(i));
    }
  }

  // return size of the heap
  public int size()
  {
    int count = 0;
    for(int i = 0; i < A.length; i++){
      if (A[i].value() != -1){
        count++;
      }
    }
    return count;
  }

  // check if heap is empty or not
  public Boolean isEmpty()
  {
    for(int i = 0; i < A.length; i++){
      if(A[i].id() != -1){
        return false;
      }
    }
    return true;
  }


  // insert specified key into the heap
  public void add(Node key)
  {
    // insert the new element to the end of the array
    A[size()] = key;

    // get element index and call heapify-up procedure
    int index = size() - 1;
    heapify_up(index);
  }

  // function to remove and return element with highest priority
  // (present at root). It returns null if queue is empty.
  public Node extractMin()
  {
    try {
      // if heap is empty, throw an exception
      if (size() == 0)
        throw new Exception("Index is out of range (Heap underflow)");

      // element with highest priority
      Node root = A[0];

      // replace the root of the heap with the last element of the array
      A[0] = A[size() - 1];
      A[size()-1] = new NullNode();

      // call heapify-down on root id
      heapify_down(0);

      // return root element
      return root;
    }
    // catch and print the exception
    catch (Exception ex) {
      return null;
    }
  }

  // function to return min/root element, but does not remove it.
  // It returns null if queue is empty
  public Node peekMin()
  {
    try {
      // if heap has no elements, throw an exception
      if (size() == 0)
        throw new Exception("Index out of range (Heap underflow)");

      // else return the top (first) element
      return A[0];
    }
    // catch the exception and print it, and return null
    catch (Exception ex) {
      return null;
    }
  }

}

/**
 * Class to represent a Node in the PriorityQueue (or a Vertex in the graph for Dijkstra's)
 */
class Node {
  public int id;
  public int cost;

  public Node(int id, int cost)
  {
    this.id = id;
    this.cost = cost;
  }

  public int value(){
    return cost;
  }

  public int id(){
    return id;
  }

  public String toString(){
    return String.format("%s[%s]", id, cost);
  }
}

/**
 * A Convenience Class to create a NullNode (so we can malloc for arrays that we don't know what
 * to put them for just yet)
 */
class NullNode extends Node{
  public NullNode(){
    super(-1,-1);
  }
}
