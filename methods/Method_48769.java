private Iterable<JanusGraphRelation> getRelations(final Change change,final Predicate<JanusGraphRelation> filter){
  Iterable<JanusGraphRelation> base;
  if (change.isProper())   base=relations.get(change);
 else   base=Iterables.concat(relations.get(Change.ADDED),relations.get(Change.REMOVED));
  return Iterables.filter(base,filter);
}
