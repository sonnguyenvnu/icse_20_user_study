@Override public void writeByteOrder(WriteBuffer out,Integer attribute){
  out.putInt(attribute - Integer.MIN_VALUE);
}
