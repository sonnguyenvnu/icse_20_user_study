/** 
 * Return the least node under <code>node</code>.
 */
public int first(int node){
  if (node == NIL) {
    return NIL;
  }
  while (true) {
    final int left=left(node);
    if (left == NIL) {
      break;
    }
    node=left;
  }
  return node;
}
