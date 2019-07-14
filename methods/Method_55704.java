private static long getCapacityOffset(){
  ByteBuffer bb=Objects.requireNonNull(NewDirectByteBuffer(-1L,MAGIC_CAPACITY));
  bb.limit(0);
  return getIntFieldOffset(bb,MAGIC_CAPACITY);
}
