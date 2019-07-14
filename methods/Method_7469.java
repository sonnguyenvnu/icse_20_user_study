public String stringValue(int columnIndex) throws SQLiteException {
  checkRow();
  return columnStringValue(preparedStatement.getStatementHandle(),columnIndex);
}
