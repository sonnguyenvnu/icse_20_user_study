@Override protected MPSTreeNode rebuild(){
  if (myHierarchyNode == null) {
    return new TextTreeNode(noNodeString());
  }
  return rebuildParentHierarchy();
}
