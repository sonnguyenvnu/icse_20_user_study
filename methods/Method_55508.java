public static void checkSafe(@Nullable CustomBuffer<?> buf,int size){
  if (buf != null) {
    checkBuffer(buf.remaining(),size);
  }
}
