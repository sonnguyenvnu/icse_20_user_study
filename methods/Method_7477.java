void checkOpened() throws SQLiteException {
  if (!isOpen) {
    throw new SQLiteException("Database closed");
  }
}
