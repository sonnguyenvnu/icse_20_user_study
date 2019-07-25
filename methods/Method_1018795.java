public boolean exists(String id){
  Cursor cursor=ConnectionManager.getDatabase(context).query(DatabaseHelper.TORRENTS_TABLE,allColumns,DatabaseHelper.COLUMN_TORRENT_ID + " = '" + id + "' ",null,null,null,null);
  if (cursor.moveToNext()) {
    cursor.close();
    return true;
  }
  cursor.close();
  return false;
}
