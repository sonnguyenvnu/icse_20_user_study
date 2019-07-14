/** 
 * Creates the composite indexes. A composite index is best used for exact match lookups.
 */
protected void createCompositeIndexes(final JanusGraphManagement management){
  management.buildIndex("nameIndex",Vertex.class).addKey(management.getPropertyKey("name")).buildCompositeIndex();
}
