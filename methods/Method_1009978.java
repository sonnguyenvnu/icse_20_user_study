/** 
 * Return the largest node under <code>node</code>.
 */
public int last(int node){
  while (true) {
    final int right=right(node);
    if (right == NIL) {
      break;
    }
    node=right;
  }
  return node;
}
