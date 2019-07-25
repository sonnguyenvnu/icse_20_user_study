protected void cache(Object id,SNode element){
  if (myDependencyHelper == null) {
    return;
  }
  if (id instanceof String) {
    myDependencyHelper.putArtifact(as_arca2u_a0a0a0b0q(id,String.class),element);
  }
 else   if (id instanceof LocalSourcePathArtifact) {
    myDependencyHelper.putArtifact(as_arca2u_a0a0a0a1a61(id,LocalSourcePathArtifact.class),element);
  }
 else   if (id instanceof SNode) {
    myDependencyHelper.putArtifact(as_arca2u_a0a0a0b1a61(id,SNode.class),element);
  }
 else {
    throw new IllegalStateException("Unexpected way to identify artifacts:" + String.valueOf(id));
  }
}
