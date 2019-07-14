public static void checkSafe(@Nullable Buffer buf,long size){
  if (buf != null) {
    checkBuffer(buf.remaining(),(int)size);
  }
}
