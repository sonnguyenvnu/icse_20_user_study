public void association(SReferenceLinkId link,String name,int index){
  myActualConcept.addLink(link,name).setIntIndex(index);
  myAssociations.put(index,MetaAdapterFactory.getReferenceLink(link,name));
  myMetaInfoProvider.setAssociationName(link,name);
}
