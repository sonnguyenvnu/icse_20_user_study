public static void checkSafe(@Nullable CustomBuffer<?> buf,long size){
  if (buf != null) {
    checkBuffer(buf.remaining(),(int)size);
  }
}
