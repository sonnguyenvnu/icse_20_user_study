void checkRow() throws SQLiteException {
  if (!inRow) {
    throw new SQLiteException("You must call next before");
  }
}
