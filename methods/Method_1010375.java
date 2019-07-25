void attach(@NotNull SNodeOwner nodeOwner){
  nodeOwner.registerNode(this);
  myOwner=nodeOwner;
  for (  SReference ref : myReferences) {
    ref.makeIndirect();
  }
  for (SNode child=firstChild(); child != null; child=child.treeNext()) {
    child.attach(nodeOwner);
  }
}
