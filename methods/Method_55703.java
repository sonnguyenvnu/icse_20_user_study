private static long getLimitOffset(){
  ByteBuffer bb=Objects.requireNonNull(NewDirectByteBuffer(-1L,MAGIC_CAPACITY));
  bb.limit(MAGIC_POSITION);
  return getIntFieldOffset(bb,MAGIC_POSITION);
}
