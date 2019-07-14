@Override public SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
  Assertions.checkState(dequeuedInputBuffer == null);
  if (availableInputBuffers.isEmpty()) {
    return null;
  }
  dequeuedInputBuffer=availableInputBuffers.pollFirst();
  return dequeuedInputBuffer;
}
