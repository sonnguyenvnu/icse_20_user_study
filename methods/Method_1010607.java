@Nullable @Override public SNode resolve(SRepository repo){
  if (myNodeId == null)   return null;
  if (myModelReference != null) {
    SModel model=myModelReference.resolve(repo);
    if (model != null) {
      SNode node=model.getNode(myNodeId);
      if (node != null) {
        return node;
      }
    }
  }
  UnregisteredNodes unregisteredNodes=UnregisteredNodes.instance();
  if (unregisteredNodes != null) {
    return unregisteredNodes.get(myModelReference,myNodeId);
  }
  return null;
}
