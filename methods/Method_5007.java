private static DownloadState merge(DownloadState downloadState,DownloadAction action){
  Assertions.checkArgument(action.type.equals(downloadState.type));
  @State int newState;
  if (action.isRemoveAction) {
    newState=DownloadState.STATE_REMOVING;
  }
 else {
    if (downloadState.state == DownloadState.STATE_REMOVING || downloadState.state == DownloadState.STATE_RESTARTING) {
      newState=DownloadState.STATE_RESTARTING;
    }
 else     if (downloadState.state == DownloadState.STATE_STOPPED) {
      newState=DownloadState.STATE_STOPPED;
    }
 else {
      newState=DownloadState.STATE_QUEUED;
    }
  }
  HashSet<StreamKey> keys=new HashSet<>(action.keys);
  Collections.addAll(keys,downloadState.streamKeys);
  StreamKey[] newKeys=keys.toArray(new StreamKey[0]);
  return new DownloadState(downloadState.id,downloadState.type,action.uri,action.customCacheKey,newState,C.PERCENTAGE_UNSET,downloadState.downloadedBytes,C.LENGTH_UNSET,downloadState.failureReason,downloadState.stopFlags,downloadState.startTimeMs,downloadState.updateTimeMs,newKeys,action.data);
}
