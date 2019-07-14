@Override public void writeByteOrder(WriteBuffer buffer,Double attribute){
  longs.writeByteOrder(buffer,NumericUtils.doubleToSortableLong(attribute));
}
