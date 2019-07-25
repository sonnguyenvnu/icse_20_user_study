public void insert(SQLiteDatabase db){
  ContentValues values=new ContentValues();
  values.put(Table.COLUMN_NAME_TITLE,"your title");
  String selection=Table.COLUMN_NAME_TITLE + " LIKE ?";
  String[] selectionArgs={"MyTitle"};
  int count=db.update(Table.TABLE_NAME,values,selection,selectionArgs);
}
