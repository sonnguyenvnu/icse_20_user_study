/** 
 * Converts a JanusGraph vertex id to the user provided id as the inverse mapping of  {@link #toVertexId(long)}.
 * @param id JanusGraph vertex id (must be positive)
 * @return original user provided id
 * @see #toVertexId(long)
 */
public long fromVertexId(long id){
  Preconditions.checkArgument(id >>> USERVERTEX_PADDING_BITWIDTH + partitionBits > 0 && id <= (vertexCountBound - 1) << USERVERTEX_PADDING_BITWIDTH + partitionBits,"Invalid vertex id provided: %s",id);
  return id >> USERVERTEX_PADDING_BITWIDTH + partitionBits;
}
