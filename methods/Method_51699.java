/** 
 * @see javax.swing.tree.TreeModel#getIndexOfChild(java.lang.Object,java.lang.Object)
 */
@Override public int getIndexOfChild(Object parent,Object child){
  Node node=(Node)parent;
  for (int i=0; i < node.jjtGetNumChildren(); i++) {
    if (node.jjtGetChild(i).equals(child)) {
      return i;
    }
  }
  return -1;
}
