public static boolean mathHasZeroByte(long value){
  return ((value - 0x0101010101010101L) & ~value & 0x8080808080808080L) != 0L;
}
