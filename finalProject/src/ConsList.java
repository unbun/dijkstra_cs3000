/**
 * A Singly-Linked List of Integers
 */
class ConsList {

  int vertex, weight;
  ConsList rest;

  public ConsList(int v, int w, ConsList r) {
    this.vertex = v;
    this.weight = w;
    this.rest = r;
  }

}
