private static long getMarkOffset(){
  ByteBuffer bb=Objects.requireNonNull(NewDirectByteBuffer(1L,0));
  return getIntFieldOffset(bb,-1);
}
