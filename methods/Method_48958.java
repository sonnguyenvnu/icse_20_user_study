/** 
 * If  {@link #isImplicitKeyQuery(org.janusgraph.graphdb.internal.RelationCategory)} is true,this method provides the result set for the query based on the evaluation of the  {@link ImplicitKey}. <p> Handling of implicit keys is completely distinct from "normal" query execution and handled extra for completeness reasons.
 * @param v
 * @return
 */
protected Iterable<JanusGraphRelation> executeImplicitKeyQuery(InternalVertex v){
  assert isImplicitKeyQuery(RelationCategory.PROPERTY);
  if (dir == Direction.IN || limit < 1)   return ImmutableList.of();
  ImplicitKey key=(ImplicitKey)tx.getRelationType(types[0]);
  return ImmutableList.of(new StandardVertexProperty(0,key,v,key.computeProperty(v),v.isNew() ? ElementLifeCycle.New : ElementLifeCycle.Loaded));
}
