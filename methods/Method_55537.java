public static boolean mathHasZeroByte(int value){
  return ((value - 0x01010101) & ~value & 0x80808080) != 0;
}
