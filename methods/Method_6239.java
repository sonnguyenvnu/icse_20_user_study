private static int pack(byte[] bytes,int offset,int length,boolean littleEndian){
  int step=1;
  if (littleEndian) {
    offset+=length - 1;
    step=-1;
  }
  int value=0;
  while (length-- > 0) {
    value=(value << 8) | (bytes[offset] & 0xFF);
    offset+=step;
  }
  return value;
}
