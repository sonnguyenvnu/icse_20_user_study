/** 
 * Returns information about a decoder suitable for audio passthrough.
 * @return A {@link MediaCodecInfo} describing the decoder, or null if no suitable decoder exists.
 * @throws DecoderQueryException If there was an error querying the available decoders.
 */
public static @Nullable MediaCodecInfo getPassthroughDecoderInfo() throws DecoderQueryException {
  MediaCodecInfo decoderInfo=getDecoderInfo(MimeTypes.AUDIO_RAW,false);
  return decoderInfo == null ? null : MediaCodecInfo.newPassthroughInstance(decoderInfo.name);
}
