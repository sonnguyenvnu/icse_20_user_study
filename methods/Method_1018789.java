public boolean add(Torrent torrent,String pathToTorrent,boolean deleteFile) throws Throwable {
  String newPath=TorrentUtils.torrentToDataDir(context,torrent.getId(),pathToTorrent);
  if (deleteFile) {
    try {
      FileUtils.forceDelete(new File(pathToTorrent));
    }
 catch (    Exception e) {
      Log.w(TAG,"Could not delete torrent file: ",e);
    }
  }
  if (newPath == null || newPath.isEmpty())   return false;
  torrent.setSource(newPath);
  return insert(torrent) >= 0;
}
