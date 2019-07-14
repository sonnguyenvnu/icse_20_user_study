public boolean getLineWrap(int row){
  return mLines[externalToInternalRow(row)].mLineWrap;
}
