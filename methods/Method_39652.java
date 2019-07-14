private static int hash(final int tag,final long value){
  return 0x7FFFFFFF & (tag + (int)value + (int)(value >>> 32));
}
