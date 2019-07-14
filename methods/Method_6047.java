/** 
 * Called immediately before an input buffer is queued into the codec.
 * @param buffer The buffer to be queued.
 */
@CallSuper @Override protected void onQueueInputBuffer(DecoderInputBuffer buffer){
  buffersInCodecCount++;
  lastInputTimeUs=Math.max(buffer.timeUs,lastInputTimeUs);
  if (Util.SDK_INT < 23 && tunneling) {
    onProcessedTunneledBuffer(buffer.timeUs);
  }
}
