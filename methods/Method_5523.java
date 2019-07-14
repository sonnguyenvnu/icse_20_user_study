private static boolean isTabCtrlCode(byte cc1,byte cc2){
  return ((cc1 & 0xF7) == 0x17) && (cc2 >= 0x21 && cc2 <= 0x23);
}
