public String compute(){
  SNode node=myLocation.getNodePointer().resolve(myRepo);
  if (node != null) {
    SNode root=node.getContainingRoot();
    return String.format("%s in %s (%s)",node,root,myLocation.getModelReference().getModelName());
  }
 else {
    return myLocation.getPresentation();
  }
}
