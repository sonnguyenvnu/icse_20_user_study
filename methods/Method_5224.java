/** 
 * Loads  {@link DrmInitData} for a given period in a DASH manifest.
 * @param dataSource The {@link HttpDataSource} from which data should be loaded.
 * @param period The {@link Period}.
 * @return The loaded {@link DrmInitData}, or null if none is defined.
 * @throws IOException Thrown when there is an error while loading.
 * @throws InterruptedException Thrown if the thread was interrupted.
 */
public static @Nullable DrmInitData loadDrmInitData(DataSource dataSource,Period period) throws IOException, InterruptedException {
  int primaryTrackType=C.TRACK_TYPE_VIDEO;
  Representation representation=getFirstRepresentation(period,primaryTrackType);
  if (representation == null) {
    primaryTrackType=C.TRACK_TYPE_AUDIO;
    representation=getFirstRepresentation(period,primaryTrackType);
    if (representation == null) {
      return null;
    }
  }
  Format manifestFormat=representation.format;
  Format sampleFormat=DashUtil.loadSampleFormat(dataSource,primaryTrackType,representation);
  return sampleFormat == null ? manifestFormat.drmInitData : sampleFormat.copyWithManifestFormatInfo(manifestFormat).drmInitData;
}
