public static void checkNT2Safe(@Nullable ByteBuffer buf){
  if (buf != null) {
    checkBuffer(buf.remaining(),2);
    assertNT(buf.get(buf.limit() - 2) == 0);
  }
}
