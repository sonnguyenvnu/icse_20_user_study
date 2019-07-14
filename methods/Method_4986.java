@Override public Downloader createDownloader(DownloadAction action){
switch (action.type) {
case DownloadAction.TYPE_PROGRESSIVE:
    return new ProgressiveDownloader(action.uri,action.customCacheKey,downloaderConstructorHelper);
case DownloadAction.TYPE_DASH:
  return createDownloader(action,DASH_DOWNLOADER_CONSTRUCTOR);
case DownloadAction.TYPE_HLS:
return createDownloader(action,HLS_DOWNLOADER_CONSTRUCTOR);
case DownloadAction.TYPE_SS:
return createDownloader(action,SS_DOWNLOADER_CONSTRUCTOR);
default :
throw new IllegalArgumentException("Unsupported type: " + action.type);
}
}
