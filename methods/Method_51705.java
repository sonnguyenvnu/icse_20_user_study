/** 
 * @see javax.swing.tree.TreeNode#isLeaf()
 */
@Override public boolean isLeaf(){
  checkChildren();
  return children.isEmpty();
}
