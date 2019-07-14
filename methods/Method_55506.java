public static void checkSafe(@Nullable Buffer buf,int size){
  if (buf != null) {
    checkBuffer(buf.remaining(),size);
  }
}
