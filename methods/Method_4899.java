/** 
 * Returns whether the decoder may throw an  {@link IllegalStateException} from{@link MediaCodec#dequeueOutputBuffer(MediaCodec.BufferInfo,long)} or{@link MediaCodec#releaseOutputBuffer(int,boolean)} after receiving an inputbuffer with  {@link MediaCodec#BUFFER_FLAG_END_OF_STREAM} set.<p> See [Internal: b/17933838].
 * @param name The name of the decoder.
 * @return True if the decoder may throw an exception after receiving an end-of-stream buffer.
 */
private static boolean codecNeedsEosOutputExceptionWorkaround(String name){
  return Util.SDK_INT == 21 && "OMX.google.aac.decoder".equals(name);
}
