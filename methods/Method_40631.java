public void addChildren(@Nullable Node... nodes){
  if (nodes != null) {
    for (    Node n : nodes) {
      if (n != null) {
        n.setParent(this);
      }
    }
  }
}
