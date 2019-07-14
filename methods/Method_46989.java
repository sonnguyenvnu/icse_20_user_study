private void removeBookmarksPath(String name,String path){
  SQLiteDatabase sqLiteDatabase=getWritableDatabase();
  sqLiteDatabase.delete(TABLE_BOOKMARKS,COLUMN_NAME + " = ? AND " + COLUMN_PATH + " = ?",new String[]{name,path});
}
