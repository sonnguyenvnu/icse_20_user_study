public void removeElement(Object elementId,MixedIndexType index,Map<String,Map<String,List<IndexEntry>>> documentsPerStore){
  Preconditions.checkArgument((index.getElement() == ElementCategory.VERTEX && elementId instanceof Long) || (index.getElement().isRelation() && elementId instanceof RelationIdentifier),"Invalid element id [%s] provided for index: %s",elementId,index);
  getDocuments(documentsPerStore,index).put(element2String(elementId),Lists.newArrayList());
}
