Collection<Element> lookup(final EntityId entitId){
  Collection<Element> results=entityIdToElements.get(entitId);
  if (null == results) {
    results=Collections.emptySet();
  }
  return results;
}
