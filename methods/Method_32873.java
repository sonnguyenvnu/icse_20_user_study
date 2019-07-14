public long getStyleAt(int externalRow,int column){
  return allocateFullLineIfNecessary(externalToInternalRow(externalRow)).getStyle(column);
}
