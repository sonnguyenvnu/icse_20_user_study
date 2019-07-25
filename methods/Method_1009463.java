/** 
 * Persist that the given quest type has been downloaded in every tile in the given tile range 
 */
public void put(Rect tiles,String questTypeName){
  SQLiteDatabase db=dbHelper.getWritableDatabase();
  db.beginTransaction();
  long time=System.currentTimeMillis();
  for (int x=tiles.left; x <= tiles.right; ++x) {
    for (int y=tiles.top; y <= tiles.bottom; ++y) {
      insert.bindLong(1,x);
      insert.bindLong(2,y);
      insert.bindString(3,questTypeName);
      insert.bindLong(4,time);
      insert.executeInsert();
      insert.clearBindings();
    }
  }
  db.setTransactionSuccessful();
  db.endTransaction();
}
