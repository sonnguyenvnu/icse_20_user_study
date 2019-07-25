@Override public void remove(String listenTo){
  w.lock();
  try {
    _removeStats.inc();
    File file=getFile(listenTo);
    if (file.exists()) {
      file.delete();
    }
 else {
      warn(_log,"file didn't exist on remove: ",file);
    }
  }
  finally {
    w.unlock();
  }
}
