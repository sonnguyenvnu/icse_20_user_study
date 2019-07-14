public static void checkSafe(@Nullable short[] buf,int size){
  if (buf != null) {
    checkBuffer(buf.length,size);
  }
}
