public TokenCodecEncoder epoch(final String value){
  final byte[] bytes;
  try {
    bytes=null == value || value.isEmpty() ? org.agrona.collections.ArrayUtil.EMPTY_BYTE_ARRAY : value.getBytes("UTF-8");
  }
 catch (  final java.io.UnsupportedEncodingException ex) {
    throw new RuntimeException(ex);
  }
  final int length=bytes.length;
  if (length > 65534) {
    throw new IllegalStateException("length > maxValue for type: " + length);
  }
  final int headerLength=2;
  final int limit=parentMessage.limit();
  parentMessage.limit(limit + headerLength + length);
  buffer.putShort(limit,(short)length,java.nio.ByteOrder.LITTLE_ENDIAN);
  buffer.putBytes(limit + headerLength,bytes,0,length);
  return this;
}
