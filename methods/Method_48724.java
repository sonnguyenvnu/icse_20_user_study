public long getKeyID(StaticBuffer b){
  long value=b.getLong(0);
  if (VertexIDType.Schema.is(value)) {
    return value;
  }
 else {
    VertexIDType type=getUserVertexIDType(value);
    long partition=partitionOffset < Long.SIZE ? value >>> partitionOffset : 0;
    long count=(value >>> USERVERTEX_PADDING_BITWIDTH) & ((1L << (partitionOffset - USERVERTEX_PADDING_BITWIDTH)) - 1);
    return constructId(count,partition,type);
  }
}
