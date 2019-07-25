public void query(SQLiteDatabase db){
  String[] projection={Table._ID,Table.COLUMN_NAME_TITLE,Table.COLUMN_NAME_SUBTITLE};
  String selection=Table.COLUMN_NAME_TITLE + " = ?";
  String[] selectionArgs={"My Title"};
  String sortOrder=Table.COLUMN_NAME_SUBTITLE + " DESC";
  Cursor c=db.query(Table.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
  c.close();
}
