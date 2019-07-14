/** 
 * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
 */
@Override public int getChildCount(Object parent){
  return ((Node)parent).jjtGetNumChildren();
}
