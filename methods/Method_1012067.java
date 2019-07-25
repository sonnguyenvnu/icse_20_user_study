public void create(Collection<SModel> models){
  ModelUnderNamespaceText nsText=new ModelUnderNamespaceText();
  ShortModelNameText shortText=new ShortModelNameText();
  myRootModelsText=myWithNamespaceNodes ? nsText : shortText;
  myChildModelsText=myWithModelsAsNamespace ? nsText : shortText;
  List<SModelTreeNode> treeNodes=getRootModelTreeNodes(models);
  if (treeNodes.isEmpty()) {
    return;
  }
  if (myWithNamespaceNodes) {
    SModelNamespaceTreeBuilder builder=new SModelNamespaceTreeBuilder();
    for (    SModelTreeNode treeNode : treeNodes) {
      builder.addNode(treeNode);
    }
    builder.fillNode(myRootTreeNode);
  }
 else {
    for (    SModelTreeNode treeNode : treeNodes) {
      myRootTreeNode.add(treeNode);
    }
  }
}
