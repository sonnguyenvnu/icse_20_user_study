public void replace(Torrent torrent,boolean deleteFile) throws Throwable {
  if (torrent == null || torrent.getSource() == null)   return;
  String newPath=TorrentUtils.torrentToDataDir(context,torrent.getId(),torrent.getSource());
  if (deleteFile) {
    try {
      FileUtils.forceDelete(new File(torrent.getSource()));
    }
 catch (    Exception e) {
      Log.w(TAG,"Could not delete torrent file: ",e);
    }
  }
  if (newPath == null)   return;
  torrent.setSource(newPath);
  update(torrent,false);
}
