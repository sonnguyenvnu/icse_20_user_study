public long longValue(int columnIndex) throws SQLiteException {
  checkRow();
  return columnLongValue(preparedStatement.getStatementHandle(),columnIndex);
}
