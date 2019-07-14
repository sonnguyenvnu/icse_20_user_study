/** 
 * Updates decoder counters to reflect that  {@code droppedBufferCount} additional buffers weredropped.
 * @param droppedBufferCount The number of additional dropped buffers.
 */
protected void updateDroppedBufferCounters(int droppedBufferCount){
  decoderCounters.droppedBufferCount+=droppedBufferCount;
  droppedFrames+=droppedBufferCount;
  consecutiveDroppedFrameCount+=droppedBufferCount;
  decoderCounters.maxConsecutiveDroppedBufferCount=Math.max(consecutiveDroppedFrameCount,decoderCounters.maxConsecutiveDroppedBufferCount);
  if (maxDroppedFramesToNotify > 0 && droppedFrames >= maxDroppedFramesToNotify) {
    maybeNotifyDroppedFrames();
  }
}
