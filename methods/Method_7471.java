public NativeByteBuffer byteBufferValue(int columnIndex) throws SQLiteException {
  checkRow();
  long ptr=columnByteBufferValue(preparedStatement.getStatementHandle(),columnIndex);
  if (ptr != 0) {
    return NativeByteBuffer.wrap(ptr);
  }
  return null;
}
