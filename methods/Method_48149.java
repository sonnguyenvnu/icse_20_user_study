/** 
 * Converts a JanusGraph vertex id of a given vertex to the user provided id as the inverse mapping of  {@link #toVertexId(long)}.
 * @param v Vertex
 * @return original user provided id
 * @deprecated Use {@link org.janusgraph.graphdb.idmanagement.IDManager#fromVertexId(long)}
 */
public static long fromVertexID(JanusGraphVertex v){
  Preconditions.checkArgument(v.hasId(),"Invalid vertex provided: %s",v);
  return fromVertexId(v.longId());
}
