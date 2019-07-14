/** 
 * Equivalent to  {@code getTrackType(getMediaMimeType(codec))}.
 * @param codec The codec.
 * @return The {@link C}{@code .TRACK_TYPE_*} constant that corresponds to a specified codec.
 */
public static int getTrackTypeOfCodec(String codec){
  return getTrackType(getMediaMimeType(codec));
}
