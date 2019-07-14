public void addChildren(List<? extends Node> nodes){
  if (nodes != null) {
    for (    Node n : nodes) {
      if (n != null) {
        n.setParent(this);
      }
    }
  }
}
