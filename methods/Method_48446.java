@Override public WriteBuffer putBytes(final StaticBuffer val){
  require(BYTE_LEN * val.length());
  val.as((array,offset,limit) -> {
    buffer.put(array,offset,val.length());
    return Boolean.TRUE;
  }
);
  return this;
}
