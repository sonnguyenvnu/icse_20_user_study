public LargeObject copy() throws SQLException {
  return new LargeObject(fp,oid,mode);
}
