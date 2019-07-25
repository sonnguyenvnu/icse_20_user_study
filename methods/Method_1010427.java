public void register(String languageNamespace,IFacet facet){
  if (MapSequence.fromMap(facetMap).containsKey(facet.getName())) {
    throw new IllegalArgumentException("already registered");
  }
  MapSequence.fromMap(facetMap).put(facet.getName(),facet);
  SetSequence.fromSet(facetsForLanguages).addElement(MultiTuple.<String,IFacet>from(languageNamespace,facet));
}
