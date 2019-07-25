/** 
 * Return the highest node that is strictly less than <code>node</code>.
 */
public final int prev(int node){
  final int left=left(node);
  if (left != NIL) {
    return last(left);
  }
 else {
    int parent=parent(node);
    while (parent != NIL && node == left(parent)) {
      node=parent;
      parent=parent(parent);
    }
    return parent;
  }
}
