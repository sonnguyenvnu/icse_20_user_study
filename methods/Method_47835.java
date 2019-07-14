@Override public void importHabitsFromFile(@NonNull File file) throws IOException {
  final Database db=opener.open(file);
  db.beginTransaction();
  createHabits(db);
  db.setTransactionSuccessful();
  db.endTransaction();
  db.close();
}
