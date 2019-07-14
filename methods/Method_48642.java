protected int getLength(ScanBuffer buffer){
  long length=VariableLong.readPositive(buffer) - 1;
  Preconditions.checkArgument(length >= -1 && length <= Integer.MAX_VALUE);
  return (int)length;
}
