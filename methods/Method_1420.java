public static DiskStorageCache buildDiskStorageCache(DiskCacheConfig diskCacheConfig,DiskStorage diskStorage){
  return buildDiskStorageCache(diskCacheConfig,diskStorage,Executors.newSingleThreadExecutor());
}
