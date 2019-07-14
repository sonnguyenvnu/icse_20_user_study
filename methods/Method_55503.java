public static void checkSafe(@Nullable long[] buf,int size){
  if (buf != null) {
    checkBuffer(buf.length,size);
  }
}
