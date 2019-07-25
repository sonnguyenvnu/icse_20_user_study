AssociationLinkInfo find(@NotNull SReferenceLinkId id){
  assert myAssociations.containsKey(id);
  return myAssociations.get(id);
}
