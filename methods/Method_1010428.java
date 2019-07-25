public void unregister(final IFacet facet){
  if (!(MapSequence.fromMap(facetMap).containsKey(facet.getName()))) {
    throw new IllegalArgumentException("not registered");
  }
  MapSequence.fromMap(facetMap).removeKey(facet.getName());
  facetsForLanguages=SetSequence.fromSetWithValues(new HashSet<Tuples._2<String,IFacet>>(),SetSequence.fromSet(facetsForLanguages).where(new IWhereFilter<Tuples._2<String,IFacet>>(){
    public boolean accept(    Tuples._2<String,IFacet> it){
      return !(facet.equals(it._1()));
    }
  }
));
}
