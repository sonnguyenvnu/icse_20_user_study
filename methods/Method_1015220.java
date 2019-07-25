public static int offset(long a,long b){
  return bitOffset(highestBit(a ^ b)) & ~0x3;
}
