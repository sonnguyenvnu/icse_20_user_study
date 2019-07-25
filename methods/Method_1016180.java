/** 
 * Reloads node childs.
 * @param node node
 */
@Override public void reload(final TreeNode node){
  tree.cancelEditing();
  clearNodeChildsCache((E)node,false);
  super.reload(node);
}
