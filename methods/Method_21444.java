@Override public boolean next() throws SQLException {
  boolean b;
  int size=this.rows.size();
  if (size == 0) {
    b=false;
  }
 else {
    this.current=null;
    this.index++;
    if (this.index > size) {
      this.index=size;
    }
 else     if (this.index < size) {
      this.current=this.rows.get(this.index);
    }
    b=this.current != null;
  }
  return b;
}
