@Override public long skip(long n){
  lock.lock();
  try {
    if (n <= 0)     return 0;
    if (filePos == eof)     return 0;
    if (filePos + n > eof)     n=(int)(eof - filePos);
    filePos+=n;
    TorrentDownload task=TorrentEngine.getInstance().getTask(stream.torrentId);
    if (task != null)     task.setInterestedPieces(stream,stream.bytesToPieceIndex(filePos + 1),1);
    return n;
  }
  finally {
    lock.unlock();
  }
}
