public CreateNote get(long id){
  SQLiteDatabase db=dbHelper.getWritableDatabase();
  String where=CreateNoteTable.Columns.ID + " = " + id;
  try (Cursor cursor=db.query(CreateNoteTable.NAME,null,where,null,null,null,null,"1")){
    if (!cursor.moveToFirst())     return null;
    return createObjectFrom(cursor);
  }
 }
