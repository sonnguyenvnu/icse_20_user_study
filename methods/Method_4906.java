/** 
 * Modifies a list of  {@link MediaCodecInfo}s to apply workarounds where we know better than the platform.
 * @param mimeType The MIME type of input media.
 * @param decoderInfos The list to modify.
 */
private static void applyWorkarounds(String mimeType,List<MediaCodecInfo> decoderInfos){
  if (MimeTypes.AUDIO_RAW.equals(mimeType)) {
    Collections.sort(decoderInfos,RAW_AUDIO_CODEC_COMPARATOR);
  }
}
