/** 
 * Returns the stream bitrate, given a frame size and the duration of that frame in microseconds.
 * @param frameSize The size of each frame in the stream.
 * @param durationUsPerFrame The duration of the given frame in microseconds.
 * @return The stream bitrate.
 */
private static int getBitrateFromFrameSize(int frameSize,long durationUsPerFrame){
  return (int)((frameSize * C.BITS_PER_BYTE * C.MICROS_PER_SECOND) / durationUsPerFrame);
}
