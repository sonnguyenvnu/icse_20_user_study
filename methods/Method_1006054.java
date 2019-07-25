@Override public void undo(){
  super.undo();
  GroupTreeNode newParent=root.getNode().getDescendant(pathToNewParent).get();
  GroupTreeNode node=newParent.getChildAt(newChildIndex).get();
  node.moveTo(root.getNode().getDescendant(pathToOldParent).get(),oldChildIndex);
}
