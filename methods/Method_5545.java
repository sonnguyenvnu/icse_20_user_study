@Override public SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
  if (availableOutputBuffers.isEmpty()) {
    return null;
  }
  while (!queuedInputBuffers.isEmpty() && queuedInputBuffers.peek().timeUs <= playbackPositionUs) {
    CeaInputBuffer inputBuffer=queuedInputBuffers.poll();
    if (inputBuffer.isEndOfStream()) {
      SubtitleOutputBuffer outputBuffer=availableOutputBuffers.pollFirst();
      outputBuffer.addFlag(C.BUFFER_FLAG_END_OF_STREAM);
      releaseInputBuffer(inputBuffer);
      return outputBuffer;
    }
    decode(inputBuffer);
    if (isNewSubtitleDataAvailable()) {
      Subtitle subtitle=createSubtitle();
      if (!inputBuffer.isDecodeOnly()) {
        SubtitleOutputBuffer outputBuffer=availableOutputBuffers.pollFirst();
        outputBuffer.setContent(inputBuffer.timeUs,subtitle,Format.OFFSET_SAMPLE_RELATIVE);
        releaseInputBuffer(inputBuffer);
        return outputBuffer;
      }
    }
    releaseInputBuffer(inputBuffer);
  }
  return null;
}
