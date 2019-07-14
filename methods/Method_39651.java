private static int hash(final int tag,final int value){
  return 0x7FFFFFFF & (tag + value);
}
