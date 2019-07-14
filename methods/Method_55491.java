public static void checkNTSafe(@Nullable int[] buf){
  if (buf != null) {
    checkBuffer(buf.length,1);
    assertNT(buf[buf.length - 1] == 0);
  }
}
