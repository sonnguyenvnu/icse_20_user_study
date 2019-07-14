/** 
 * Returns a  {@link Format} that is the same as the input format but includes information from thespecified sources of metadata.
 */
public static Format getFormatWithMetadata(int trackType,Format format,@Nullable Metadata udtaMetadata,@Nullable Metadata mdtaMetadata,GaplessInfoHolder gaplessInfoHolder){
  if (trackType == C.TRACK_TYPE_AUDIO) {
    if (gaplessInfoHolder.hasGaplessInfo()) {
      format=format.copyWithGaplessInfo(gaplessInfoHolder.encoderDelay,gaplessInfoHolder.encoderPadding);
    }
    if (udtaMetadata != null) {
      format=format.copyWithMetadata(udtaMetadata);
    }
  }
 else   if (trackType == C.TRACK_TYPE_VIDEO && mdtaMetadata != null) {
    for (int i=0; i < mdtaMetadata.length(); i++) {
      Metadata.Entry entry=mdtaMetadata.get(i);
      if (entry instanceof MdtaMetadataEntry) {
        MdtaMetadataEntry mdtaMetadataEntry=(MdtaMetadataEntry)entry;
        if (MDTA_KEY_ANDROID_CAPTURE_FPS.equals(mdtaMetadataEntry.key) && mdtaMetadataEntry.typeIndicator == MDTA_TYPE_INDICATOR_FLOAT) {
          try {
            float fps=ByteBuffer.wrap(mdtaMetadataEntry.value).asFloatBuffer().get();
            format=format.copyWithFrameRate(fps);
            format=format.copyWithMetadata(new Metadata(mdtaMetadataEntry));
          }
 catch (          NumberFormatException e) {
            Log.w(TAG,"Ignoring invalid framerate");
          }
        }
      }
    }
  }
  return format;
}
