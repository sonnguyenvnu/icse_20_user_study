/** 
 * Loads initialization data for the  {@code representation} and returns the sample {@link Format}.
 * @param dataSource The source from which the data should be loaded.
 * @param trackType The type of the representation. Typically one of the {@link com.google.android.exoplayer2.C} {@code TRACK_TYPE_*} constants.
 * @param representation The representation which initialization chunk belongs to.
 * @return the sample {@link Format} of the given representation.
 * @throws IOException Thrown when there is an error while loading.
 * @throws InterruptedException Thrown if the thread was interrupted.
 */
public static @Nullable Format loadSampleFormat(DataSource dataSource,int trackType,Representation representation) throws IOException, InterruptedException {
  ChunkExtractorWrapper extractorWrapper=loadInitializationData(dataSource,trackType,representation,false);
  return extractorWrapper == null ? null : extractorWrapper.getSampleFormats()[0];
}
