@Override public int getRow() throws SQLException {
  return (this.index < 0) || isAfterLast() || this.rows.isEmpty() ? 0 : this.index + 1;
}
