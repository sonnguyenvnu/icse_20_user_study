private static boolean isMiscCode(byte cc1,byte cc2){
  return ((cc1 & 0xF7) == 0x14) && ((cc2 & 0xF0) == 0x20);
}
