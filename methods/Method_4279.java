/** 
 * Returns  {@code buffer} or a copy of it, such that there is enough space in the returned bufferto store  {@code newFrameCount} additional frames.
 * @param buffer The buffer.
 * @param frameCount The number of frames already in the buffer.
 * @param additionalFrameCount The number of additional frames that need to be stored in thebuffer.
 * @return A buffer with enough space for the additional frames.
 */
private short[] ensureSpaceForAdditionalFrames(short[] buffer,int frameCount,int additionalFrameCount){
  int currentCapacityFrames=buffer.length / channelCount;
  if (frameCount + additionalFrameCount <= currentCapacityFrames) {
    return buffer;
  }
 else {
    int newCapacityFrames=3 * currentCapacityFrames / 2 + additionalFrameCount;
    return Arrays.copyOf(buffer,newCapacityFrames * channelCount);
  }
}
