@Override public void touch(){
  if (getVirtualFile() != null) {
    getVirtualFile().setModificationStamp(LocalTimeCounter.currentTime());
  }
}
