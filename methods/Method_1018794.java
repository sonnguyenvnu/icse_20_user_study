public boolean exists(Torrent torrent){
  Cursor cursor=ConnectionManager.getDatabase(context).query(DatabaseHelper.TORRENTS_TABLE,allColumns,DatabaseHelper.COLUMN_TORRENT_ID + " = '" + torrent.getId() + "' ",null,null,null,null);
  if (cursor.moveToNext()) {
    cursor.close();
    return true;
  }
  cursor.close();
  return false;
}
