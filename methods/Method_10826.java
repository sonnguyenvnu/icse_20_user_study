private static boolean isPNG(byte[] b){
  return b.length >= 8 && (b[0] == (byte)137 && b[1] == (byte)80 && b[2] == (byte)78 && b[3] == (byte)71 && b[4] == (byte)13 && b[5] == (byte)10 && b[6] == (byte)26 && b[7] == (byte)10);
}
