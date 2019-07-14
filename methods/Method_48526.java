private static long convertFromUnsigned(final long value){
  return ((value & 1) == 1) ? -(value >>> 1) : value >>> 1;
}
