private void writeCacheEntry(PrintStream writer,CountingMemoryCacheInspector.DumpInfoEntry<CacheKey,CloseableImage> entry){
  if (!(entry.key instanceof BitmapMemoryCacheKey)) {
    writer.println("Undefined: " + entry.key.getClass());
  }
  BitmapMemoryCacheKey cacheKey=(BitmapMemoryCacheKey)entry.key;
  writer.println(formatStrLocaleSafe("size: %7.2fkB (%4d x %4d) key: %s, %s, duration: %dms",entry.value.get().getSizeInBytes() / KB,entry.value.get().getWidth(),entry.value.get().getHeight(),entry.key,cacheKey.getCallerContext(),RealtimeSinceBootClock.get().now() - cacheKey.getInBitmapCacheSince()));
}
