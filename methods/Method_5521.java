private static boolean isMidrowCtrlCode(byte cc1,byte cc2){
  return ((cc1 & 0xF7) == 0x11) && ((cc2 & 0xF0) == 0x20);
}
