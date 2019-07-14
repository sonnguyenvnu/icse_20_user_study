public static boolean mathHasZeroShort(long value){
  return ((value - 0x0001000100010001L) & ~value & 0x8000800080008000L) != 0L;
}
