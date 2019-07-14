void checkFinalized() throws SQLiteException {
  if (isFinalized) {
    throw new SQLiteException("Prepared query finalized");
  }
}
