@Override public void remove(){
  if (fileName != null) {
    if (tempFile != null) {
      tempFile.stopAutoDelete();
    }
synchronized (handler.getLobSyncObject()) {
      FileUtils.delete(fileName);
    }
  }
  if (handler != null) {
    handler.getLobStorage().removeLob(this);
  }
}
