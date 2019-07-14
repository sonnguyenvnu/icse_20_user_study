/** 
 * Returns a copy of  {@code codecs} without the codecs whose track type doesn't match {@code trackType}.
 * @param codecs A codec sequence string, as defined in RFC 6381.
 * @param trackType One of {@link C}{@code .TRACK_TYPE_*}.
 * @return A copy of {@code codecs} without the codecs whose track type doesn't match {@code trackType}.
 */
public static @Nullable String getCodecsOfType(String codecs,int trackType){
  String[] codecArray=splitCodecs(codecs);
  if (codecArray.length == 0) {
    return null;
  }
  StringBuilder builder=new StringBuilder();
  for (  String codec : codecArray) {
    if (trackType == MimeTypes.getTrackTypeOfCodec(codec)) {
      if (builder.length() > 0) {
        builder.append(",");
      }
      builder.append(codec);
    }
  }
  return builder.length() > 0 ? builder.toString() : null;
}
