@Override public Object read(ScanBuffer buffer){
  Preconditions.checkArgument(buffer.getByte() == 1,"Invalid serialization state");
  return new Object();
}
