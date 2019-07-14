public TerminalRow allocateFullLineIfNecessary(int row){
  return (mLines[row] == null) ? (mLines[row]=new TerminalRow(mColumns,0)) : mLines[row];
}
