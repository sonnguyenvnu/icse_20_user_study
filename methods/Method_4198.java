private void processBuffers(long avSyncPresentationTimeUs) throws WriteException {
  int count=activeAudioProcessors.length;
  int index=count;
  while (index >= 0) {
    ByteBuffer input=index > 0 ? outputBuffers[index - 1] : (inputBuffer != null ? inputBuffer : AudioProcessor.EMPTY_BUFFER);
    if (index == count) {
      writeBuffer(input,avSyncPresentationTimeUs);
    }
 else {
      AudioProcessor audioProcessor=activeAudioProcessors[index];
      audioProcessor.queueInput(input);
      ByteBuffer output=audioProcessor.getOutput();
      outputBuffers[index]=output;
      if (output.hasRemaining()) {
        index++;
        continue;
      }
    }
    if (input.hasRemaining()) {
      return;
    }
    index--;
  }
}
