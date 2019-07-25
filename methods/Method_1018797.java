public void download(Torrent torrent){
  if (magnets.contains(torrent.getId()))   cancelFetchMagnet(torrent.getId());
  File saveDir=new File(torrent.getDownloadPath());
  if (torrent.isDownloadingMetadata()) {
    addTorrentsQueue.put(torrent.getId(),torrent);
    download(torrent.getSource(),saveDir);
    return;
  }
  TorrentInfo ti=new TorrentInfo(new File(torrent.getSource()));
  List<Priority> priorities=torrent.getFilePriorities();
  if (priorities == null || priorities.size() != ti.numFiles())   throw new IllegalArgumentException("File count doesn't match: " + torrent.getName());
  TorrentDownload task=torrentTasks.get(torrent.getId());
  if (task != null)   task.remove(false);
  String dataDir=TorrentUtils.findTorrentDataDir(context,torrent.getId());
  File resumeFile=null;
  if (dataDir != null) {
    File file=new File(dataDir,TorrentStorage.Model.DATA_TORRENT_RESUME_FILE_NAME);
    if (file.exists())     resumeFile=file;
  }
  addTorrentsQueue.put(ti.infoHash().toString(),torrent);
  download(ti,saveDir,resumeFile,priorities.toArray(new Priority[priorities.size()]),null);
}
