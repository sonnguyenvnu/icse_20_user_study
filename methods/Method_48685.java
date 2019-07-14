@Override public void writeByteOrder(WriteBuffer buffer,String attribute){
  if (attribute == null) {
    buffer.putByte((byte)-1);
    return;
  }
 else {
    buffer.putByte((byte)0);
  }
  for (int i=0; i < attribute.length(); i++) {
    char c=attribute.charAt(i);
    Preconditions.checkArgument(((int)c) > 0,"No null characters allowed in string @ position %s: %s",i,attribute);
    cs.writeByteOrder(buffer,c);
  }
  cs.writeByteOrder(buffer,(char)0);
}
