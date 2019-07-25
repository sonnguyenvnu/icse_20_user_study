@Override public boolean accept(final Key key,final Value value){
  final byte[] rowID=key.getRowData().getBackingArray();
  final byte flag=rowID[rowID.length - 1];
  if (!entities) {
    return checkEdge(flag,key);
  }
 else {
    boolean foundDelimiter=false;
    for (    final byte aRowID : rowID) {
      if (aRowID == ByteArrayEscapeUtils.DELIMITER) {
        foundDelimiter=true;
        break;
      }
    }
    return !foundDelimiter || checkEdge(flag,key);
  }
}
