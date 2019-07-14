@Override public Integer readByteOrder(ScanBuffer buffer){
  return buffer.getInt() + Integer.MIN_VALUE;
}
