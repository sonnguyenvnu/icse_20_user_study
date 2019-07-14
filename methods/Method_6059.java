/** 
 * Renders the output buffer with the specified index. This method is only called if the platform API version of the device is less than 21.
 * @param codec The codec that owns the output buffer.
 * @param index The index of the output buffer to drop.
 * @param presentationTimeUs The presentation time of the output buffer, in microseconds.
 */
protected void renderOutputBuffer(MediaCodec codec,int index,long presentationTimeUs){
  maybeNotifyVideoSizeChanged();
  TraceUtil.beginSection("releaseOutputBuffer");
  codec.releaseOutputBuffer(index,true);
  TraceUtil.endSection();
  lastRenderTimeUs=SystemClock.elapsedRealtime() * 1000;
  decoderCounters.renderedOutputBufferCount++;
  consecutiveDroppedFrameCount=0;
  maybeNotifyRenderedFirstFrame();
}
