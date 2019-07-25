public void delete(final SQLiteDatabase db){
  db.execSQL("DROP TABLE IF EXISTS " + mTableName);
}
