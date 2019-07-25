@Override public void redo(){
  super.redo();
  GroupTreeNode oldParent=root.getNode().getDescendant(pathToOldParent).get();
  GroupTreeNode node=oldParent.getChildAt(oldChildIndex).get();
  node.moveTo(root.getNode().getDescendant(pathToNewParent).get(),newChildIndex);
}
