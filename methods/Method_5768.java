private static byte[] getBytes(Object value){
  if (value instanceof Long) {
    return ByteBuffer.allocate(8).putLong((Long)value).array();
  }
 else   if (value instanceof String) {
    return ((String)value).getBytes(Charset.forName(C.UTF8_NAME));
  }
 else   if (value instanceof byte[]) {
    return (byte[])value;
  }
 else {
    throw new IllegalArgumentException();
  }
}
