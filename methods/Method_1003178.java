/** 
 * Get the next node if there is one.
 * @param x the node
 * @return the next node or null
 */
private static TreeNode next(TreeNode x){
  if (x == null) {
    return null;
  }
  TreeNode r=x.right;
  if (r != null) {
    x=r;
    TreeNode l=x.left;
    while (l != null) {
      x=l;
      l=x.left;
    }
    return x;
  }
  TreeNode ch=x;
  x=x.parent;
  while (x != null && ch == x.right) {
    ch=x;
    x=x.parent;
  }
  return x;
}
