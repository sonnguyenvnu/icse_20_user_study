public static DiskStorageCache buildDiskStorageCache(DiskCacheConfig diskCacheConfig,DiskStorage diskStorage,Executor executorForBackgroundInit){
  DiskStorageCache.Params params=new DiskStorageCache.Params(diskCacheConfig.getMinimumSizeLimit(),diskCacheConfig.getLowDiskSpaceSizeLimit(),diskCacheConfig.getDefaultSizeLimit());
  return new DiskStorageCache(diskStorage,diskCacheConfig.getEntryEvictionComparatorSupplier(),params,diskCacheConfig.getCacheEventListener(),diskCacheConfig.getCacheErrorLogger(),diskCacheConfig.getDiskTrimmableRegistry(),diskCacheConfig.getContext(),executorForBackgroundInit,diskCacheConfig.getIndexPopulateAtStartupEnabled());
}
