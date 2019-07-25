public void attach(@NotNull ProjectModuleTreeNode node){
  myListAccess.acquireUninterruptibly();
  try {
    myNodes.add(node);
  }
  finally {
    myListAccess.release();
  }
  myTreeHighlighter.refreshModuleTreeNodes(Collections.singleton(node));
}
