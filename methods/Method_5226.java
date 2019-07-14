/** 
 * Loads initialization data for the  {@code representation} and optionally index data then returnsa  {@link ChunkExtractorWrapper} which contains the output.
 * @param dataSource The source from which the data should be loaded.
 * @param trackType The type of the representation. Typically one of the {@link com.google.android.exoplayer2.C} {@code TRACK_TYPE_*} constants.
 * @param representation The representation which initialization chunk belongs to.
 * @param loadIndex Whether to load index data too.
 * @return A {@link ChunkExtractorWrapper} for the {@code representation}, or null if no initialization or (if requested) index data exists.
 * @throws IOException Thrown when there is an error while loading.
 * @throws InterruptedException Thrown if the thread was interrupted.
 */
private static @Nullable ChunkExtractorWrapper loadInitializationData(DataSource dataSource,int trackType,Representation representation,boolean loadIndex) throws IOException, InterruptedException {
  RangedUri initializationUri=representation.getInitializationUri();
  if (initializationUri == null) {
    return null;
  }
  ChunkExtractorWrapper extractorWrapper=newWrappedExtractor(trackType,representation.format);
  RangedUri requestUri;
  if (loadIndex) {
    RangedUri indexUri=representation.getIndexUri();
    if (indexUri == null) {
      return null;
    }
    requestUri=initializationUri.attemptMerge(indexUri,representation.baseUrl);
    if (requestUri == null) {
      loadInitializationData(dataSource,representation,extractorWrapper,initializationUri);
      requestUri=indexUri;
    }
  }
 else {
    requestUri=initializationUri;
  }
  loadInitializationData(dataSource,representation,extractorWrapper,requestUri);
  return extractorWrapper;
}
