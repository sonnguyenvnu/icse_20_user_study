public List<Tab> getAllTabs(){
  List<Tab> tabList=new ArrayList<>();
  SQLiteDatabase sqLiteDatabase=getReadableDatabase();
  Cursor cursor=sqLiteDatabase.query(TABLE_TAB,null,null,null,null,null,null);
  boolean hasNext=cursor.moveToFirst();
  while (hasNext) {
    Tab tab=new Tab(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
    tabList.add(tab);
    hasNext=cursor.moveToNext();
  }
  cursor.close();
  return tabList;
}
