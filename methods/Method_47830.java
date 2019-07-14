@Override public void importHabitsFromFile(@NonNull File file) throws IOException {
  Database db=opener.open(file);
  db.beginTransaction();
  createHabits(db);
  db.setTransactionSuccessful();
  db.endTransaction();
  db.close();
}
