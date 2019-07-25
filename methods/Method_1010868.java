MPSPsiNodeBase next(MPSPsiNodeBase prev){
  assert prev.getEntry().list() == this;
  return prev.getEntry().next.node;
}
