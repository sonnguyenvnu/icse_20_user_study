private static boolean isJPEG(byte[] b){
  return b.length >= 2 && (b[0] == (byte)0xFF) && (b[1] == (byte)0xD8);
}
