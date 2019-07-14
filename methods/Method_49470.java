/** 
 * Creates the mixed indexes. A mixed index requires that an external indexing backend is configured on the graph instance. A mixed index is best for full text search, numerical range, and geospatial queries.
 */
protected void createMixedIndexes(final JanusGraphManagement management){
  if (useMixedIndex) {
    management.buildIndex("vAge",Vertex.class).addKey(management.getPropertyKey("age")).buildMixedIndex(mixedIndexConfigName);
    management.buildIndex("eReasonPlace",Edge.class).addKey(management.getPropertyKey("reason")).addKey(management.getPropertyKey("place")).buildMixedIndex(mixedIndexConfigName);
  }
}
