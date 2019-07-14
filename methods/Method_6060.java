/** 
 * Renders the output buffer with the specified index. This method is only called if the platform API version of the device is 21 or later.
 * @param codec The codec that owns the output buffer.
 * @param index The index of the output buffer to drop.
 * @param presentationTimeUs The presentation time of the output buffer, in microseconds.
 * @param releaseTimeNs The wallclock time at which the frame should be displayed, in nanoseconds.
 */
@TargetApi(21) protected void renderOutputBufferV21(MediaCodec codec,int index,long presentationTimeUs,long releaseTimeNs){
  maybeNotifyVideoSizeChanged();
  TraceUtil.beginSection("releaseOutputBuffer");
  codec.releaseOutputBuffer(index,releaseTimeNs);
  TraceUtil.endSection();
  lastRenderTimeUs=SystemClock.elapsedRealtime() * 1000;
  decoderCounters.renderedOutputBufferCount++;
  consecutiveDroppedFrameCount=0;
  maybeNotifyRenderedFirstFrame();
}
