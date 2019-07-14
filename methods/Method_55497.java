public static void checkNTSafe(@Nullable FloatBuffer buf){
  if (buf != null) {
    checkBuffer(buf.remaining(),1);
    assertNT(buf.get(buf.limit() - 1) == 0.0f);
  }
}
