/** 
 * Initializes the download, returning a list of  {@link Segment}s that need to be downloaded. 
 */
@SuppressWarnings("NonAtomicVolatileUpdate") private List<Segment> initDownload() throws IOException, InterruptedException {
  M manifest=getManifest(dataSource,manifestDataSpec);
  if (!streamKeys.isEmpty()) {
    manifest=manifest.copy(streamKeys);
  }
  List<Segment> segments=getSegments(dataSource,manifest,false);
  CachingCounters cachingCounters=new CachingCounters();
  totalSegments=segments.size();
  downloadedSegments=0;
  downloadedBytes=0;
  long totalBytes=0;
  for (int i=segments.size() - 1; i >= 0; i--) {
    Segment segment=segments.get(i);
    CacheUtil.getCached(segment.dataSpec,cache,cacheKeyFactory,cachingCounters);
    downloadedBytes+=cachingCounters.alreadyCachedBytes;
    if (cachingCounters.contentLength != C.LENGTH_UNSET) {
      if (cachingCounters.alreadyCachedBytes == cachingCounters.contentLength) {
        downloadedSegments++;
        segments.remove(i);
      }
      if (totalBytes != C.LENGTH_UNSET) {
        totalBytes+=cachingCounters.contentLength;
      }
    }
 else {
      totalBytes=C.LENGTH_UNSET;
    }
  }
  this.totalBytes=totalBytes;
  return segments;
}
