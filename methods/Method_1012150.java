@Override public void populate(SNodeTreeNode treeNode){
  if (myProjectPane.showNodeStructure()) {
    SNode n=treeNode.getSNode();
    if (n == null || n.getModel() == null) {
      return;
    }
    treeNode.add(new ConceptTreeNode(n));
    treeNode.add(new PropertiesTreeNode(n));
    treeNode.add(new ReferencesTreeNode(n));
    for (    SNode child : n.getChildren()) {
      treeNode.add(treeNode.createChildTreeNode(child));
    }
  }
}
