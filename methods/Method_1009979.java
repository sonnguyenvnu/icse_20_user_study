/** 
 * Return the least node that is strictly greater than <code>node</code>.
 */
public final int next(int node){
  final int right=right(node);
  if (right != NIL) {
    return first(right);
  }
 else {
    int parent=parent(node);
    while (parent != NIL && node == right(parent)) {
      node=parent;
      parent=parent(parent);
    }
    return parent;
  }
}
