/** 
 * Returns the earliest byte position in [position, limit) of  {@code buffer} that contains a frameclassified as a noisy frame, or the limit of the buffer if no such frame exists.
 */
private int findNoisePosition(ByteBuffer buffer){
  for (int i=buffer.position() + 1; i < buffer.limit(); i+=2) {
    if (Math.abs(buffer.get(i)) > SILENCE_THRESHOLD_LEVEL_MSB) {
      return bytesPerFrame * (i / bytesPerFrame);
    }
  }
  return buffer.limit();
}
