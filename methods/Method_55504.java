public static void checkSafe(@Nullable float[] buf,int size){
  if (buf != null) {
    checkBuffer(buf.length,size);
  }
}
