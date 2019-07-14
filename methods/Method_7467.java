public double doubleValue(int columnIndex) throws SQLiteException {
  checkRow();
  return columnDoubleValue(preparedStatement.getStatementHandle(),columnIndex);
}
