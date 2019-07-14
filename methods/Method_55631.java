private static long address(int position,int elementShift,long address){
  return address + ((position & 0xFFFF_FFFFL) << elementShift);
}
