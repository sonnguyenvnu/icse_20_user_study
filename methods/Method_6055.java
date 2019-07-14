/** 
 * Returns whether to force rendering an output buffer.
 * @param earlyUs The time until the current buffer should be presented in microseconds. Anegative value indicates that the buffer is late.
 * @param elapsedSinceLastRenderUs The elapsed time since the last output buffer was rendered, inmicroseconds.
 * @return Returns whether to force rendering an output buffer.
 */
protected boolean shouldForceRenderOutputBuffer(long earlyUs,long elapsedSinceLastRenderUs){
  return isBufferLate(earlyUs) && elapsedSinceLastRenderUs > 100000;
}
