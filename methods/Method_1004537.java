public Cursor select(int rows,int offset){
  final SQLiteDatabase db=getDb();
  if (db != null)   return db.rawQuery("select " + COLUMN_KEY + "," + COLUMN_EXPIRES + "," + COLUMN_PROVIDER + " from " + TABLE + " limit ? offset ?",new String[]{rows + "",offset + ""});
  return null;
}
