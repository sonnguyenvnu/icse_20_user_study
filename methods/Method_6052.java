/** 
 * Called when an output buffer is successfully processed.
 * @param presentationTimeUs The timestamp associated with the output buffer.
 */
@CallSuper @Override protected void onProcessedOutputBuffer(long presentationTimeUs){
  buffersInCodecCount--;
  while (pendingOutputStreamOffsetCount != 0 && presentationTimeUs >= pendingOutputStreamSwitchTimesUs[0]) {
    outputStreamOffsetUs=pendingOutputStreamOffsetsUs[0];
    pendingOutputStreamOffsetCount--;
    System.arraycopy(pendingOutputStreamOffsetsUs,1,pendingOutputStreamOffsetsUs,0,pendingOutputStreamOffsetCount);
    System.arraycopy(pendingOutputStreamSwitchTimesUs,1,pendingOutputStreamSwitchTimesUs,0,pendingOutputStreamOffsetCount);
  }
}
