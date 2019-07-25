public AssociationLinkInfo find(@NotNull SReferenceLink link){
  SReferenceLinkId id=MetaIdHelper.getAssociation(link);
  return myRegistry.get(id.getConceptId()).find(id);
}
