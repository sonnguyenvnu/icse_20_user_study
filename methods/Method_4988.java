@Override public DownloadStateCursor getDownloadStates(@DownloadState.State int... states){
  return getDownloadStateTable().get(states);
}
