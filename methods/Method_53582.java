public void addTag(CharSequence tag){
  ContentValues values=new ContentValues();
  SQLiteDatabase db=mDbHelper.getWritableDatabase();
  values.put(TagsTable.TAG,tag.toString());
  db.insert(TagsTable.TABLE_NAME,null,values);
  db.close();
}
