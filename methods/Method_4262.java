/** 
 * Returns the earliest byte position in [position, limit) of  {@code buffer} such that all framesfrom the byte position to the limit are classified as silent.
 */
private int findNoiseLimit(ByteBuffer buffer){
  for (int i=buffer.limit() - 1; i >= buffer.position(); i-=2) {
    if (Math.abs(buffer.get(i)) > SILENCE_THRESHOLD_LEVEL_MSB) {
      return bytesPerFrame * (i / bytesPerFrame) + bytesPerFrame;
    }
  }
  return buffer.position();
}
