/** 
 * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
 */
@Override public boolean isLeaf(Object node){
  return ((Node)node).jjtGetNumChildren() == 0;
}
