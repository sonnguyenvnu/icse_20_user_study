/** 
 * Determines whether the existing  {@link MediaCodec} can be kept for a new format, and if it canwhether it requires reconfiguration. <p>The default implementation returns  {@link #KEEP_CODEC_RESULT_NO}.
 * @param codec The existing {@link MediaCodec} instance.
 * @param codecInfo A {@link MediaCodecInfo} describing the decoder.
 * @param oldFormat The format for which the existing instance is configured.
 * @param newFormat The new format.
 * @return Whether the instance can be kept, and if it can whether it requires reconfiguration.
 */
protected @KeepCodecResult int canKeepCodec(MediaCodec codec,MediaCodecInfo codecInfo,Format oldFormat,Format newFormat){
  return KEEP_CODEC_RESULT_NO;
}
