/** 
 * Returns whether the underlying library supports the specified MIME type.
 * @param mimeType The MIME type to check.
 * @param encoding The PCM encoding for raw audio.
 */
public static boolean supportsFormat(String mimeType,@C.PcmEncoding int encoding){
  String codecName=getCodecName(mimeType,encoding);
  return codecName != null && ffmpegHasDecoder(codecName);
}
