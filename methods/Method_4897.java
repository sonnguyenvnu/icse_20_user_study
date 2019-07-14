/** 
 * Returns whether the decoder is known to handle the propagation of the  {@link MediaCodec#BUFFER_FLAG_END_OF_STREAM} flag incorrectly on the host device.<p>If true is returned, the renderer will work around the issue by approximating end of stream behavior without relying on the flag being propagated through to an output buffer by the underlying decoder.
 * @param codecInfo Information about the {@link MediaCodec}.
 * @return True if the decoder is known to handle {@link MediaCodec#BUFFER_FLAG_END_OF_STREAM}propagation incorrectly on the host device. False otherwise.
 */
private static boolean codecNeedsEosPropagationWorkaround(MediaCodecInfo codecInfo){
  String name=codecInfo.name;
  return (Util.SDK_INT <= 17 && ("OMX.rk.video_decoder.avc".equals(name) || "OMX.allwinner.video.decoder.avc".equals(name))) || ("Amazon".equals(Util.MANUFACTURER) && "AFTS".equals(Util.MODEL) && codecInfo.secure);
}
