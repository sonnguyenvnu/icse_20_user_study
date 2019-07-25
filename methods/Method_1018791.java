public void replace(Torrent torrent,byte[] bencode) throws Throwable {
  if (torrent == null || bencode == null)   return;
  String newPath=TorrentUtils.torrentToDataDir(context,torrent.getId(),bencode);
  if (newPath == null)   return;
  torrent.setSource(newPath);
  update(torrent,false);
}
