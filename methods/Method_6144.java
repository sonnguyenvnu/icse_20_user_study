public static byte[] decodeQuotedPrintable(final byte[] bytes){
  if (bytes == null) {
    return null;
  }
  final ByteArrayOutputStream buffer=new ByteArrayOutputStream();
  for (int i=0; i < bytes.length; i++) {
    final int b=bytes[i];
    if (b == '=') {
      try {
        final int u=Character.digit((char)bytes[++i],16);
        final int l=Character.digit((char)bytes[++i],16);
        buffer.write((char)((u << 4) + l));
      }
 catch (      Exception e) {
        FileLog.e(e);
        return null;
      }
    }
 else {
      buffer.write(b);
    }
  }
  byte[] array=buffer.toByteArray();
  try {
    buffer.close();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return array;
}
