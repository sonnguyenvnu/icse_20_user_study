private static <Q extends BaseVertexQuery>Q addIndexSchemaConstraint(Q query,IndexType indexType){
  if (indexType.hasSchemaTypeConstraint()) {
    JanusGraphSchemaType constraint=indexType.getSchemaTypeConstraint();
    Preconditions.checkArgument(constraint instanceof RelationType,"Expected constraint to be a " + "relation type: %s",constraint);
    query.types((RelationType)constraint);
  }
  return query;
}
