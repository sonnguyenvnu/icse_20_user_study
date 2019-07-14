public static void checkNTSafe(@Nullable IntBuffer buf){
  if (buf != null) {
    checkBuffer(buf.remaining(),1);
    assertNT(buf.get(buf.limit() - 1) == 0);
  }
}
