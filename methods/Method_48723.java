public StaticBuffer getKey(long vertexId){
  if (VertexIDType.Schema.is(vertexId)) {
    return BufferUtil.getLongBuffer(vertexId);
  }
 else {
    assert isUserVertexId(vertexId);
    VertexIDType type=getUserVertexIDType(vertexId);
    assert type.offset() == USERVERTEX_PADDING_BITWIDTH;
    long partition=getPartitionId(vertexId);
    long count=vertexId >>> (partitionBits + USERVERTEX_PADDING_BITWIDTH);
    assert count > 0;
    long keyId=(partition << partitionOffset) | type.addPadding(count);
    return BufferUtil.getLongBuffer(keyId);
  }
}
