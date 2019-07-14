private static long getAllocationSize(int elements,int elementShift){
  return apiCheckAllocation(elements,Integer.toUnsignedLong(elements) << elementShift,BITS64 ? Long.MAX_VALUE : 0xFFFF_FFFFL);
}
