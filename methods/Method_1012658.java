@Override public boolean fetch(){
synchronized (downloadLock) {
    return resolveSubtitleFile();
  }
}
