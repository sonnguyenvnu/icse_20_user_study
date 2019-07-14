public static boolean mathHasZeroShort(int value){
  return (value & 0xFFFF) == 0 || (value >>> 16) == 0;
}
