public static String bytesToHex(ByteBuffer bytes){
  final int offset=bytes.position();
  final int size=bytes.remaining();
  final char[] c=new char[size * 2];
  for (int i=0; i < size; i++) {
    final int byteAsInteger=bytes.get(i + offset);
    c[i * 2]=Hex.byteToChar[(byteAsInteger & 0xf0) >> 4];
    c[1 + i * 2]=Hex.byteToChar[byteAsInteger & 0x0f];
  }
  return Hex.wrapCharArray(c);
}
