private static boolean isBMP(byte[] b){
  return b.length >= 2 && (b[0] == 0x42) && (b[1] == 0x4d);
}
