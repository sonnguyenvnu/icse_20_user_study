/** 
 * Returns whether the decoder is known to behave incorrectly if flushed after receiving an input buffer with  {@link MediaCodec#BUFFER_FLAG_END_OF_STREAM} set.<p> If true is returned, the renderer will work around the issue by instantiating a new decoder when this case occurs. <p> See [Internal: b/8578467, b/23361053].
 * @param name The name of the decoder.
 * @return True if the decoder is known to behave incorrectly if flushed after receiving an inputbuffer with  {@link MediaCodec#BUFFER_FLAG_END_OF_STREAM} set. False otherwise.
 */
private static boolean codecNeedsEosFlushWorkaround(String name){
  return (Util.SDK_INT <= 23 && "OMX.google.vorbis.decoder".equals(name)) || (Util.SDK_INT <= 19 && ("hb2000".equals(Util.DEVICE) || "stvm8".equals(Util.DEVICE)) && ("OMX.amlogic.avc.decoder.awesome".equals(name) || "OMX.amlogic.avc.decoder.awesome.secure".equals(name)));
}
