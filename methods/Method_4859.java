/** 
 * Returns whether it may be possible to adapt to playing a different format when the codec is configured to play media in the specified  {@code format}. For adaptation to succeed, the codec must also be configured with appropriate maximum values and  {@link #isSeamlessAdaptationSupported(Format,Format,boolean)} must return {@code true} for theold/new formats.
 * @param format The format of media for which the decoder will be configured.
 * @return Whether adaptation may be possible
 */
public boolean isSeamlessAdaptationSupported(Format format){
  if (isVideo) {
    return adaptive;
  }
 else {
    Pair<Integer,Integer> codecProfileLevel=MediaCodecUtil.getCodecProfileAndLevel(format.codecs);
    return codecProfileLevel != null && codecProfileLevel.first == CodecProfileLevel.AACObjectXHE;
  }
}
