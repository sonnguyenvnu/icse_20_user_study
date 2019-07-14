@Override public void getQueries(QueryContainer queries){
  if (index instanceof RelationTypeIndex) {
    queries.addQuery().types(indexRelationTypeName).direction(Direction.OUT).relations();
  }
 else   if (index instanceof JanusGraphIndex) {
    IndexType indexType=managementSystem.getSchemaVertex(index).asIndexType();
switch (indexType.getElement()) {
case PROPERTY:
      addIndexSchemaConstraint(queries.addQuery(),indexType).properties();
    break;
case VERTEX:
  queries.addQuery().properties();
queries.addQuery().type(BaseLabel.VertexLabelEdge).direction(Direction.OUT).edges();
break;
case EDGE:
indexType.hasSchemaTypeConstraint();
addIndexSchemaConstraint(queries.addQuery().direction(Direction.OUT),indexType).edges();
break;
default :
throw new AssertionError("Unexpected category: " + indexType.getElement());
}
}
 else throw new UnsupportedOperationException("Unsupported index found: " + index);
}
