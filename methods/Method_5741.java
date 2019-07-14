/** 
 * Copies the given span with an updated last access time. Passed span becomes invalid after this call.
 * @param cacheSpan Span to be copied and updated.
 * @return a span with the updated last access time.
 * @throws CacheException If renaming of the underlying span file failed.
 */
public SimpleCacheSpan touch(SimpleCacheSpan cacheSpan) throws CacheException {
  SimpleCacheSpan newCacheSpan=cacheSpan.copyWithUpdatedLastAccessTime(id);
  if (!cacheSpan.file.renameTo(newCacheSpan.file)) {
    throw new CacheException("Renaming of " + cacheSpan.file + " to " + newCacheSpan.file + " failed.");
  }
  Assertions.checkState(cachedSpans.remove(cacheSpan));
  cachedSpans.add(newCacheSpan);
  return newCacheSpan;
}
