@Override public void queueInput(ByteBuffer inputBuffer){
  Assertions.checkState(outputChannels != null);
  int position=inputBuffer.position();
  int limit=inputBuffer.limit();
  int frameCount=(limit - position) / (2 * channelCount);
  int outputSize=frameCount * outputChannels.length * 2;
  if (buffer.capacity() < outputSize) {
    buffer=ByteBuffer.allocateDirect(outputSize).order(ByteOrder.nativeOrder());
  }
 else {
    buffer.clear();
  }
  while (position < limit) {
    for (    int channelIndex : outputChannels) {
      buffer.putShort(inputBuffer.getShort(position + 2 * channelIndex));
    }
    position+=channelCount * 2;
  }
  inputBuffer.position(limit);
  buffer.flip();
  outputBuffer=buffer;
}
