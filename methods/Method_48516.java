private static void writeUnsigned(WriteBuffer out,int offset,final long value){
  assert offset % 7 == 0;
  while (offset > 0) {
    offset-=7;
    byte b=(byte)((value >>> offset) & BIT_MASK);
    if (offset == 0) {
      b=(byte)(b | STOP_MASK);
    }
    out.putByte(b);
  }
}
