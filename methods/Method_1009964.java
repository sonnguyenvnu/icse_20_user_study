/** 
 * Return the last node whose centroid is less than <code>centroid</code>.
 */
@SuppressWarnings("WeakerAccess") public int floor(double centroid){
  int floor=IntAVLTree.NIL;
  for (int node=tree.root(); node != IntAVLTree.NIL; ) {
    final int cmp=Double.compare(centroid,mean(node));
    if (cmp <= 0) {
      node=tree.left(node);
    }
 else {
      floor=node;
      node=tree.right(node);
    }
  }
  return floor;
}
