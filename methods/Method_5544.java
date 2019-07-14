@Override public void queueInputBuffer(SubtitleInputBuffer inputBuffer) throws SubtitleDecoderException {
  Assertions.checkArgument(inputBuffer == dequeuedInputBuffer);
  if (inputBuffer.isDecodeOnly()) {
    releaseInputBuffer(dequeuedInputBuffer);
  }
 else {
    dequeuedInputBuffer.queuedInputBufferCount=queuedInputBufferCount++;
    queuedInputBuffers.add(dequeuedInputBuffer);
  }
  dequeuedInputBuffer=null;
}
