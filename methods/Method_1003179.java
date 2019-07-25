/** 
 * Get the previous node if there is one.
 * @param x the node
 * @return the previous node or null
 */
private static TreeNode previous(TreeNode x){
  if (x == null) {
    return null;
  }
  TreeNode l=x.left;
  if (l != null) {
    x=l;
    TreeNode r=x.right;
    while (r != null) {
      x=r;
      r=x.right;
    }
    return x;
  }
  TreeNode ch=x;
  x=x.parent;
  while (x != null && ch == x.left) {
    ch=x;
    x=x.parent;
  }
  return x;
}
