public T get(long id){
  SQLiteDatabase db=dbHelper.getReadableDatabase();
  String where=getIdColumnName() + " = " + id;
  try (Cursor cursor=db.query(getMergedViewName(),null,where,null,null,null,null,"1")){
    if (!cursor.moveToFirst())     return null;
    return createObjectFrom(cursor);
  }
 }
