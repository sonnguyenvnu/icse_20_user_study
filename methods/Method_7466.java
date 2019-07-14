public int intValue(int columnIndex) throws SQLiteException {
  checkRow();
  return columnIntValue(preparedStatement.getStatementHandle(),columnIndex);
}
