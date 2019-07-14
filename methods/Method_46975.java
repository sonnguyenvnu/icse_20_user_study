public Tab findTab(int tabNo){
  String query="Select * FROM " + TABLE_TAB + " WHERE " + COLUMN_TAB_NO + "= \"" + tabNo + "\"";
  SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
  Cursor cursor=sqLiteDatabase.rawQuery(query,null);
  Tab tab;
  if (cursor.moveToFirst()) {
    tab=new Tab(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
    cursor.close();
  }
 else {
    tab=null;
  }
  return tab;
}
