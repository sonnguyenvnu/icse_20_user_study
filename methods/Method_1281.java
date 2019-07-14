public static ImageRequest.RequestLevel convertCacheLevelToRequestLevel(AbstractDraweeControllerBuilder.CacheLevel cacheLevel){
switch (cacheLevel) {
case FULL_FETCH:
    return ImageRequest.RequestLevel.FULL_FETCH;
case DISK_CACHE:
  return ImageRequest.RequestLevel.DISK_CACHE;
case BITMAP_MEMORY_CACHE:
return ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE;
default :
throw new RuntimeException("Cache level" + cacheLevel + "is not supported. ");
}
}
