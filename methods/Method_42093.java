/** 
 * @param status 1 for EXCLUDED, 2 for INCLUDED
 * @return
 */
public ArrayList<String> getFolders(int status){
  ArrayList<String> list=new ArrayList<>();
  SQLiteDatabase db=getReadableDatabase();
  Cursor cur=db.query(TABLE_ALBUMS,new String[]{ALBUM_PATH},ALBUM_STATUS + "=?",new String[]{String.valueOf(status)},null,null,null);
  if (cur.moveToFirst())   do   list.add(cur.getString(0));
 while (cur.moveToNext());
  cur.close();
  db.close();
  return list;
}
