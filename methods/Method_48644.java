@Override public boolean[] read(ScanBuffer buffer){
  int length=getLength(buffer);
  if (length < 0)   return null;
  boolean[] result=new boolean[length];
  int b=0;
  for (int i=0; i < length; i++) {
    int offset=i % 8;
    if (offset == 0) {
      b=0xFF & buffer.getByte();
    }
    result[i]=BooleanSerializer.decode((byte)((b >>> (7 - offset)) & 1));
  }
  return result;
}
