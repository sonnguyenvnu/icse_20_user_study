private static boolean isPreambleAddressCode(byte cc1,byte cc2){
  return ((cc1 & 0xF0) == 0x10) && ((cc2 & 0xC0) == 0x40);
}
