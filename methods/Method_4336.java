private void releaseInputBufferInternal(I inputBuffer){
  inputBuffer.clear();
  availableInputBuffers[availableInputBufferCount++]=inputBuffer;
}
