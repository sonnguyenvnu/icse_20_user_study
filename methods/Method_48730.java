/** 
 * Converts a user provided long id into a JanusGraph vertex id. The id must be positive and less than  {@link #getVertexCountBound()}. This method is useful when providing ids during vertex creation via  {@link org.apache.tinkerpop.gremlin.structure.Graph#addVertex(Object)}.
 * @param id long id
 * @return a corresponding JanusGraph vertex id
 * @see #fromVertexId(long)
 */
public long toVertexId(long id){
  Preconditions.checkArgument(id > 0,"Vertex id must be positive: %s",id);
  Preconditions.checkArgument(vertexCountBound > id,"Vertex id is too large: %s",id);
  return id << (partitionBits + USERVERTEX_PADDING_BITWIDTH);
}
