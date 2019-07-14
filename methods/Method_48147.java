/** 
 * Converts a user provided long id into a JanusGraph vertex id. The id must be positive and can be at most 61 bits long. This method is useful when providing ids during vertex creation via  {@link org.apache.tinkerpop.gremlin.structure.Graph#addVertex(Object)}.
 * @param id long id
 * @return a corresponding JanusGraph vertex id
 * @deprecated Use {@link org.janusgraph.graphdb.idmanagement.IDManager#toVertexId(long)}.
 */
public static long toVertexId(long id){
  Preconditions.checkArgument(id > 0,"Vertex id must be positive: %s",id);
  Preconditions.checkArgument(IDManager.VertexIDType.NormalVertex.removePadding(Long.MAX_VALUE) >= id,"Vertex id is too large: %s",id);
  return IDManager.VertexIDType.NormalVertex.addPadding(id);
}
