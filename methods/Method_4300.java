@Override public void queueInput(ByteBuffer inputBuffer){
  Assertions.checkState(sonic != null);
  if (inputBuffer.hasRemaining()) {
    ShortBuffer shortBuffer=inputBuffer.asShortBuffer();
    int inputSize=inputBuffer.remaining();
    inputBytes+=inputSize;
    sonic.queueInput(shortBuffer);
    inputBuffer.position(inputBuffer.position() + inputSize);
  }
  int outputSize=sonic.getFramesAvailable() * channelCount * 2;
  if (outputSize > 0) {
    if (buffer.capacity() < outputSize) {
      buffer=ByteBuffer.allocateDirect(outputSize).order(ByteOrder.nativeOrder());
      shortBuffer=buffer.asShortBuffer();
    }
 else {
      buffer.clear();
      shortBuffer.clear();
    }
    sonic.getOutput(shortBuffer);
    outputBytes+=outputSize;
    buffer.limit(outputSize);
    outputBuffer=buffer;
  }
}
