public boolean isLoadingStream(){
  return stream != null && stream.isWaitingForLoad();
}
