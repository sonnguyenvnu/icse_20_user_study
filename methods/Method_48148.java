/** 
 * Converts a JanusGraph vertex id to the user provided id as the inverse mapping of  {@link #toVertexId(long)}.
 * @param id JanusGraph vertex id (must be positive)
 * @return original user provided id
 * @deprecated Use {@link org.janusgraph.graphdb.idmanagement.IDManager#fromVertexId(long)}
 */
public static long fromVertexId(long id){
  Preconditions.checkArgument(id > 0,"Invalid vertex id provided: %s",id);
  return IDManager.VertexIDType.NormalVertex.removePadding(id);
}
