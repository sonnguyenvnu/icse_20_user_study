public static StaticBuffer readBuffer(ScanBuffer in){
  long length=VariableLong.readPositive(in);
  Preconditions.checkArgument(length >= 0 && length <= Integer.MAX_VALUE);
  byte[] data=in.getBytes((int)length);
  assert data.length == length;
  return new StaticArrayBuffer(data);
}
