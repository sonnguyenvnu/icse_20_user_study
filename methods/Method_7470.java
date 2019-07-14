public byte[] byteArrayValue(int columnIndex) throws SQLiteException {
  checkRow();
  return columnByteArrayValue(preparedStatement.getStatementHandle(),columnIndex);
}
