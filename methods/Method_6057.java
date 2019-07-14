/** 
 * Drops frames from the current output buffer to the next keyframe at or before the playback position. If no such keyframe exists, as the playback position is inside the same group of pictures as the buffer being processed, returns  {@code false}. Returns  {@code true} otherwise.
 * @param codec The codec that owns the output buffer.
 * @param index The index of the output buffer to drop.
 * @param presentationTimeUs The presentation time of the output buffer, in microseconds.
 * @param positionUs The current playback position, in microseconds.
 * @return Whether any buffers were dropped.
 * @throws ExoPlaybackException If an error occurs flushing the codec.
 */
protected boolean maybeDropBuffersToKeyframe(MediaCodec codec,int index,long presentationTimeUs,long positionUs) throws ExoPlaybackException {
  int droppedSourceBufferCount=skipSource(positionUs);
  if (droppedSourceBufferCount == 0) {
    return false;
  }
  decoderCounters.droppedToKeyframeCount++;
  updateDroppedBufferCounters(buffersInCodecCount + droppedSourceBufferCount);
  flushOrReinitCodec();
  return true;
}
