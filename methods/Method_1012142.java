public void attach(@NotNull SModelTreeNode node){
  final SModel model=node.getModel();
  if (model != null) {
    boolean modelSeenFirstTime=true;
synchronized (myTreeNodes) {
      Collection<SModelTreeNode> knownNodes=myTreeNodes.get(model.getReference());
      if (knownNodes == null) {
        myTreeNodes.put(model.getReference(),knownNodes=new ArrayList<>(3));
      }
 else {
        modelSeenFirstTime=false;
      }
      knownNodes.add(node);
    }
    if (modelSeenFirstTime) {
      ((SModelInternal)model).addModelListener(myModelChangeListener);
    }
  }
  refreshTreeNodes(Collections.singleton(node));
}
