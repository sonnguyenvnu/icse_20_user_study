@Override public boolean isBeforeFirst() throws SQLException {
  return this.index == -1 && this.rows.size() != 0;
}
