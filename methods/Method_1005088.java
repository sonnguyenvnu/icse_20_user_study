Collection<Element> lookup(final EdgeId edgeId){
  Collection<Element> results=edgeIdToElements.get(edgeId);
  if (null == results) {
    results=Collections.emptySet();
  }
  return results;
}
