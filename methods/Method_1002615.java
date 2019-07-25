public String epoch(){
  final int headerLength=2;
  final int limit=parentMessage.limit();
  final int dataLength=(int)(buffer.getShort(limit,java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF);
  parentMessage.limit(limit + headerLength + dataLength);
  if (0 == dataLength) {
    return "";
  }
  final byte[] tmp=new byte[dataLength];
  buffer.getBytes(limit + headerLength,tmp,0,dataLength);
  final String value;
  try {
    value=new String(tmp,"UTF-8");
  }
 catch (  final java.io.UnsupportedEncodingException ex) {
    throw new RuntimeException(ex);
  }
  return value;
}
