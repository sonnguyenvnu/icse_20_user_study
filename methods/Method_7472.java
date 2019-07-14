public int getTypeOf(int columnIndex) throws SQLiteException {
  checkRow();
  return columnType(preparedStatement.getStatementHandle(),columnIndex);
}
