public void association(String id,String name,String index){
  assert myActualConcept != null;
  SReferenceLinkId linkId=myIdEncoder.parseAssociation(myActualConcept.getConceptId(),id);
  myActualConcept.addLink(linkId,name);
  myAssociations.put(index,MetaAdapterFactory.getReferenceLink(linkId,name));
  myMetaInfoProvider.setAssociationName(linkId,name);
}
