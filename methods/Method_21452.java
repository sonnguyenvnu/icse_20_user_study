@Override public boolean last() throws SQLException {
  boolean b=true;
  int size=this.rows.size();
  if (size == 0) {
    b=false;
  }
 else {
    this.index=size - 1;
    this.current=this.rows.get(this.index);
  }
  return b;
}
