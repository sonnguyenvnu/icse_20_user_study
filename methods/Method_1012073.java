@Override protected MPSTreeNode rebuild(){
  TextTreeNode root=new TextTreeNode("root");
  if (myScope != null) {
    for (    SModule module : myScope.getModules()) {
      root.add(ProjectModuleTreeNode.createFor(myParent.getProject(),module));
    }
    SModelTreeNode.LongModelNameText modelText=new SModelTreeNode.LongModelNameText();
    for (    SModel model : myScope.getModels()) {
      root.add(new SModelTreeNode(model,modelText));
    }
    for (    SNode node : myScope.getRoots()) {
      root.add(new SNodeTreeNode(node,null));
    }
  }
  setRootVisible(false);
  setShowsRootHandles(true);
  return root;
}
