protected void releaseOutputBuffer(SubtitleOutputBuffer outputBuffer){
  outputBuffer.clear();
  availableOutputBuffers.add(outputBuffer);
}
