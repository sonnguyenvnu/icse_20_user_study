public void detach(@NotNull ProjectModuleTreeNode node){
  myListAccess.acquireUninterruptibly();
  try {
    myNodes.remove(node);
  }
  finally {
    myListAccess.release();
  }
}
