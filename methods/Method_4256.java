/** 
 * Incrementally processes new input from  {@code inputBuffer} while in {@link #STATE_MAYBE_SILENT}, updating the state if needed.
 */
private void processMaybeSilence(ByteBuffer inputBuffer){
  int limit=inputBuffer.limit();
  int noisePosition=findNoisePosition(inputBuffer);
  int maybeSilenceInputSize=noisePosition - inputBuffer.position();
  int maybeSilenceBufferRemaining=maybeSilenceBuffer.length - maybeSilenceBufferSize;
  if (noisePosition < limit && maybeSilenceInputSize < maybeSilenceBufferRemaining) {
    output(maybeSilenceBuffer,maybeSilenceBufferSize);
    maybeSilenceBufferSize=0;
    state=STATE_NOISY;
  }
 else {
    int bytesToWrite=Math.min(maybeSilenceInputSize,maybeSilenceBufferRemaining);
    inputBuffer.limit(inputBuffer.position() + bytesToWrite);
    inputBuffer.get(maybeSilenceBuffer,maybeSilenceBufferSize,bytesToWrite);
    maybeSilenceBufferSize+=bytesToWrite;
    if (maybeSilenceBufferSize == maybeSilenceBuffer.length) {
      if (hasOutputNoise) {
        output(maybeSilenceBuffer,paddingSize);
        skippedFrames+=(maybeSilenceBufferSize - paddingSize * 2) / bytesPerFrame;
      }
 else {
        skippedFrames+=(maybeSilenceBufferSize - paddingSize) / bytesPerFrame;
      }
      updatePaddingBuffer(inputBuffer,maybeSilenceBuffer,maybeSilenceBufferSize);
      maybeSilenceBufferSize=0;
      state=STATE_SILENT;
    }
    inputBuffer.limit(limit);
  }
}
