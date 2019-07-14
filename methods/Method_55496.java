public static void checkNTSafe(@Nullable IntBuffer buf,int terminator){
  if (buf != null) {
    checkBuffer(buf.remaining(),1);
    assertNT(buf.get(buf.limit() - 1) == terminator);
  }
}
