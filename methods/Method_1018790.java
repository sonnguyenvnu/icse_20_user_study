private long insert(Torrent torrent){
  ContentValues values=new ContentValues();
  values.put(DatabaseHelper.COLUMN_TORRENT_ID,torrent.getId());
  values.put(DatabaseHelper.COLUMN_NAME,torrent.getName());
  values.put(DatabaseHelper.COLUMN_PATH_TO_TORRENT,torrent.getSource());
  values.put(DatabaseHelper.COLUMN_PATH_TO_DOWNLOAD,torrent.getDownloadPath());
  values.put(DatabaseHelper.COLUMN_FILE_PRIORITIES,prioritiesToString(torrent.getFilePriorities()));
  values.put(DatabaseHelper.COLUMN_IS_SEQUENTIAL,(torrent.isSequentialDownload() ? 1 : 0));
  values.put(DatabaseHelper.COLUMN_IS_FINISHED,(torrent.isFinished() ? 1 : 0));
  values.put(DatabaseHelper.COLUMN_IS_PAUSED,(torrent.isPaused() ? 1 : 0));
  values.put(DatabaseHelper.COLUMN_DOWNLOADING_METADATA,(torrent.isDownloadingMetadata() ? 1 : 0));
  values.put(DatabaseHelper.COLUMN_DATETIME,torrent.getDateAdded());
  values.put(DatabaseHelper.COLUMN_ERROR,torrent.getError());
  return ConnectionManager.getDatabase(context).insert(DatabaseHelper.TORRENTS_TABLE,null,values);
}
