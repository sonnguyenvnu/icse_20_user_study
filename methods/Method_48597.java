public static IndexType getGraphIndexDirect(String name,StandardJanusGraphTx transaction){
  JanusGraphSchemaVertex v=transaction.getSchemaVertex(JanusGraphSchemaCategory.GRAPHINDEX.getSchemaName(name));
  if (v == null)   return null;
  return v.asIndexType();
}
