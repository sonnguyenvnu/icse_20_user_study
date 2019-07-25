@Override public boolean accept(final Key key,final Value value){
  byte[] vertices=key.getRowData().getBackingArray();
  int pos=-1;
  for (int i=vertices.length - 3; i > 0; --i) {
    if (vertices[i] == ByteArrayEscapeUtils.DELIMITER) {
      pos=i;
      break;
    }
  }
  if (pos == -1) {
    return true;
  }
  return filter.membershipTest(new org.apache.hadoop.util.bloom.Key(Arrays.copyOfRange(vertices,pos + 1,vertices.length - 2)));
}
