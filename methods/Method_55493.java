public static void checkNT1Safe(@Nullable ByteBuffer buf){
  if (buf != null) {
    checkBuffer(buf.remaining(),1);
    assertNT(buf.get(buf.limit() - 1) == 0);
  }
}
