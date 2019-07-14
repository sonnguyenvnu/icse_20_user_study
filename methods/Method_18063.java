private static int getXFromRoot(InternalNode node){
  if (node == null) {
    return 0;
  }
  return node.getX() + getXFromRoot(parent(node));
}
