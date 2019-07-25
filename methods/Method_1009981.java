/** 
 * Find a node in this tree.
 */
public int find(){
  for (int node=root; node != NIL; ) {
    final int cmp=compare(node);
    if (cmp < 0) {
      node=left(node);
    }
 else     if (cmp > 0) {
      node=right(node);
    }
 else {
      return node;
    }
  }
  return NIL;
}
