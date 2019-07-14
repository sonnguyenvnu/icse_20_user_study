/** 
 * Incrementally processes new input from  {@code inputBuffer} while in {@link #STATE_SILENT}, updating the state if needed.
 */
private void processSilence(ByteBuffer inputBuffer){
  int limit=inputBuffer.limit();
  int noisyPosition=findNoisePosition(inputBuffer);
  inputBuffer.limit(noisyPosition);
  skippedFrames+=inputBuffer.remaining() / bytesPerFrame;
  updatePaddingBuffer(inputBuffer,paddingBuffer,paddingSize);
  if (noisyPosition < limit) {
    output(paddingBuffer,paddingSize);
    state=STATE_NOISY;
    inputBuffer.limit(limit);
  }
}
