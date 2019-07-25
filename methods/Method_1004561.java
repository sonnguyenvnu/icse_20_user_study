@Override public void save(Context ctx,SharedPreferences prefs){
  SharedPreferences.Editor edit=prefs.edit();
  edit.putString("osmdroid.basePath",getOsmdroidBasePath().getAbsolutePath());
  edit.putString("osmdroid.cachePath",getOsmdroidTileCache().getAbsolutePath());
  edit.putBoolean("osmdroid.DebugMode",isDebugMode());
  edit.putBoolean("osmdroid.DebugDownloading",isDebugMapTileDownloader());
  edit.putBoolean("osmdroid.DebugMapView",isDebugMapView());
  edit.putBoolean("osmdroid.DebugTileProvider",isDebugTileProviders());
  edit.putBoolean("osmdroid.HardwareAcceleration",isMapViewHardwareAccelerated());
  edit.putBoolean("osmdroid.TileDownloaderFollowRedirects",isMapTileDownloaderFollowRedirects());
  edit.putString("osmdroid.userAgentValue",getUserAgentValue());
  save(prefs,edit,mAdditionalHttpRequestProperties,"osmdroid.additionalHttpRequestProperty.");
  edit.putLong("osmdroid.gpsWaitTime",gpsWaitTime);
  edit.putInt("osmdroid.cacheMapTileCount",cacheMapTileCount);
  edit.putInt("osmdroid.tileDownloadThreads",tileDownloadThreads);
  edit.putInt("osmdroid.tileFileSystemThreads",tileFileSystemThreads);
  edit.putInt("osmdroid.tileDownloadMaxQueueSize",tileDownloadMaxQueueSize);
  edit.putInt("osmdroid.tileFileSystemMaxQueueSize",tileFileSystemMaxQueueSize);
  edit.putLong("osmdroid.ExpirationExtendedDuration",expirationAdder);
  if (expirationOverride != null)   edit.putLong("osmdroid.ExpirationOverride",expirationOverride);
  edit.putInt("osmdroid.ZoomSpeedDefault",animationSpeedDefault);
  edit.putInt("osmdroid.animationSpeedShort",animationSpeedShort);
  edit.putBoolean("osmdroid.mapViewRecycler",mapViewRecycler);
  edit.putInt("osmdroid.cacheTileOvershoot",cacheTileOvershoot);
  commit(edit);
}
