private static SimpleResultSet wrap(SimpleResultSet rs,TriggerRowSource source,Object[] row) throws SQLException {
  if (row == null) {
    return null;
  }
  source.setRow(row);
  rs.next();
  return rs;
}
