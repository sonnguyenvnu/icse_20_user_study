public void detach(@NotNull SModelTreeNode node){
  final SModel model=node.getModel();
  if (model != null) {
    boolean modelSeenLastTime=false;
synchronized (myTreeNodes) {
      Collection<SModelTreeNode> knownNodes=myTreeNodes.get(model.getReference());
      if (knownNodes != null) {
        knownNodes.remove(node);
        if (knownNodes.isEmpty()) {
          myTreeNodes.remove(model.getReference());
          modelSeenLastTime=true;
        }
      }
    }
    if (modelSeenLastTime) {
      ((SModelInternal)model).removeModelListener(myModelChangeListener);
    }
  }
}
