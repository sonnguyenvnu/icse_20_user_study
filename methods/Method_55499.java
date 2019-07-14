public static void checkNTSafe(@Nullable PointerBuffer buf,long terminator){
  if (buf != null) {
    checkBuffer(buf.remaining(),1);
    assertNT(buf.get(buf.limit() - 1) == terminator);
  }
}
