@Override public Byte read(ScanBuffer buffer){
  return (byte)(buffer.getByte() + Byte.MIN_VALUE);
}
