private Downloader createDownloader(DownloadAction action,@Nullable Constructor<? extends Downloader> constructor){
  if (constructor == null) {
    throw new IllegalStateException("Module missing for: " + action.type);
  }
  try {
    return constructor.newInstance(action.uri,action.getKeys(),downloaderConstructorHelper);
  }
 catch (  Exception e) {
    throw new RuntimeException("Failed to instantiate downloader for: " + action.type,e);
  }
}
