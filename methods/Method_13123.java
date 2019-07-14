@SuppressWarnings("unchecked") protected void populate(T node){
  if ((node instanceof TreeNodeExpandable) && !expanded.contains(node.getUri())) {
    ((TreeNodeExpandable)node).populateTreeNode(api);
    expanded.add(node.getUri());
    int i=node.getChildCount();
    while (i-- > 0) {
      if (!accepted.contains(((T)node.getChildAt(i)).getUri())) {
        node.remove(i);
      }
    }
  }
}
