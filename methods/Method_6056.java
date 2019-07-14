/** 
 * Drops the output buffer with the specified index.
 * @param codec The codec that owns the output buffer.
 * @param index The index of the output buffer to drop.
 * @param presentationTimeUs The presentation time of the output buffer, in microseconds.
 */
protected void dropOutputBuffer(MediaCodec codec,int index,long presentationTimeUs){
  TraceUtil.beginSection("dropVideoBuffer");
  codec.releaseOutputBuffer(index,false);
  TraceUtil.endSection();
  updateDroppedBufferCounters(1);
}
