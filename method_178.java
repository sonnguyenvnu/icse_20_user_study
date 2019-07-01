public static int _XXXXX_(final byte[] bytes,final int offset,final ByteOrder byteOrder){
  final int byte0=0xff & bytes[offset + 0];
  final int byte1=0xff & bytes[offset + 1];
  final int byte2=0xff & bytes[offset + 2];
  final int byte3=0xff & bytes[offset + 3];
  if (byteOrder == ByteOrder.BIG_ENDIAN) {
    return (byte0 << 24) | (byte1 << 16) | (byte2 << 8)| byte3;
  }
 else {
    return (byte3 << 24) | (byte2 << 16) | (byte1 << 8)| byte0;
  }
}