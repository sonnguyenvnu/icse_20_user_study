private static DownloadState convert(DownloadAction action){
  long currentTimeMs=System.currentTimeMillis();
  return new DownloadState(action.id,action.type,action.uri,action.customCacheKey,action.isRemoveAction ? DownloadState.STATE_REMOVING : DownloadState.STATE_QUEUED,C.PERCENTAGE_UNSET,0,C.LENGTH_UNSET,DownloadState.FAILURE_REASON_NONE,0,currentTimeMs,currentTimeMs,action.keys.toArray(new StreamKey[0]),action.data);
}
