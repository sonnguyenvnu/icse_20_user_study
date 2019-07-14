private static long getIntFieldOffset(ByteBuffer bb,int magicValue){
  long offset=4L;
  while (true) {
    if (UNSAFE.getInt(bb,offset) == magicValue) {
      return offset;
    }
    offset+=4L;
  }
}
