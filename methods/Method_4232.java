/** 
 * Returns whether encoded audio passthrough should be used for playing back the input format. This implementation returns true if the  {@link AudioSink} indicates that encoded audio outputis supported.
 * @param channelCount The number of channels in the input media, or {@link Format#NO_VALUE} ifnot known.
 * @param mimeType The type of input media.
 * @return Whether passthrough playback is supported.
 */
protected boolean allowPassthrough(int channelCount,String mimeType){
  return audioSink.supportsOutput(channelCount,MimeTypes.getEncoding(mimeType));
}
