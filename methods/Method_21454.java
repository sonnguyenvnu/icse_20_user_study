@Override public boolean relative(int rows) throws SQLException {
  int size=this.rows.size();
  if (size == 0) {
    return false;
  }
  this.index+=rows;
  if (this.index < -1) {
    this.index=-1;
  }
 else   if (this.index > size) {
    this.index=size;
  }
  this.current=(this.index < 0) || (this.index >= size) ? null : this.rows.get(this.index);
  return !isAfterLast() && !isBeforeFirst();
}
