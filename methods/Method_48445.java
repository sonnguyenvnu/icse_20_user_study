@Override public WriteBuffer putBytes(byte[] val){
  require(BYTE_LEN * val.length);
  buffer.put(val);
  return this;
}
