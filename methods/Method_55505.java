public static void checkSafe(@Nullable double[] buf,int size){
  if (buf != null) {
    checkBuffer(buf.length,size);
  }
}
