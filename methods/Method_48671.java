@Override public void writeByteOrder(WriteBuffer buffer,T attribute){
  stringSerializer.writeByteOrder(buffer,attribute.toString());
}
