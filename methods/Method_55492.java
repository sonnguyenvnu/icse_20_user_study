public static void checkNTSafe(@Nullable long[] buf){
  if (buf != null) {
    checkBuffer(buf.length,1);
    assertNT(buf[buf.length - 1] == NULL);
  }
}
