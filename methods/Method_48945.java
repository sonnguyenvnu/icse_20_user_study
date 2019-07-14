private Q addConstraint(String type,JanusGraphPredicate rel,Object value){
  Preconditions.checkArgument(StringUtils.isNotBlank(type));
  Preconditions.checkNotNull(rel);
  if (type.equals(ImplicitKey.ADJACENT_ID.name())) {
    Preconditions.checkArgument(rel == Cmp.EQUAL,"Only equality constraints are supported for %s",type);
    long vertexId=ElementUtils.getVertexId(value);
    Preconditions.checkArgument(vertexId > 0,"Expected valid vertex id: %s",value);
    return adjacent(getVertex(vertexId));
  }
 else   if (type.equals(ImplicitKey.ID.name())) {
    RelationIdentifier rid=ElementUtils.getEdgeId(value);
    Preconditions.checkNotNull(rid,"Expected valid relation id: %s",value);
    return addConstraint(ImplicitKey.JANUSGRAPHID.name(),rel,rid.getRelationId());
  }
 else {
    Preconditions.checkArgument(rel.isValidCondition(value),"Invalid condition provided: " + value);
  }
  if (constraints == NO_CONSTRAINTS)   constraints=new ArrayList<>(5);
  constraints.add(new PredicateCondition<>(type,rel,value));
  return getThis();
}
