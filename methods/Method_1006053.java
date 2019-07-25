@Override public void redo(){
  super.redo();
  final GroupTreeNode subtreeRoot=m_groupRoot.getDescendant(m_subtreeRootPath).get();
  subtreeRoot.removeAllChildren();
  for (  GroupTreeNode modifiedNode : m_modifiedSubtree) {
    modifiedNode.moveTo(subtreeRoot);
  }
}
