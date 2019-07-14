public static String bytesToHex(byte... bytes){
  char[] c=new char[bytes.length * 2];
  for (int i=0; i < bytes.length; i++) {
    int byteAsInteger=bytes[i];
    c[i * 2]=byteToChar[(byteAsInteger & 0xf0) >> 4];
    c[1 + i * 2]=byteToChar[byteAsInteger & 0x0f];
  }
  return wrapCharArray(c);
}
