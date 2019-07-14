@Override public boolean previous() throws SQLException {
  int rowIndex=this.index;
  boolean b;
  if ((rowIndex - 1) >= 0) {
    rowIndex--;
    this.index=rowIndex;
    this.current=(rowIndex < 0) || (rowIndex >= this.rows.size()) ? null : (this.rows.get(rowIndex));
    b=true;
  }
 else   if ((rowIndex - 1) == -1) {
    rowIndex--;
    this.index=rowIndex;
    this.current=null;
    b=false;
  }
 else {
    b=false;
  }
  return b;
}
