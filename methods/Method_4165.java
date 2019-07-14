/** 
 * Returns whether this device supports playback of the specified audio  {@code encoding}.
 * @param encoding One of {@link android.media.AudioFormat}'s  {@code ENCODING_*} constants.
 * @return Whether this device supports playback the specified audio {@code encoding}.
 */
public boolean supportsEncoding(int encoding){
  return Arrays.binarySearch(supportedEncodings,encoding) >= 0;
}
