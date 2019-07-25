@Override public MACAddress value(ByteBuffer bytes){
  byte[] data=new byte[length];
  bytes.get(data);
  ByteBuffer buffer=ByteBuffer.wrap(data);
  buffer.limit(length - 2);
  return new MACAddress(buffer);
}
