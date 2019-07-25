public boolean contains(SNode node){
  if (node == null) {
    return false;
  }
  SNode root=node.getContainingRoot();
  if (CollectionSequence.fromCollection(myRoots).contains(root.getReference()) || CollectionSequence.fromCollection(myRoots).contains(node.getReference())) {
    return true;
  }
  return contains(root.getModel());
}
