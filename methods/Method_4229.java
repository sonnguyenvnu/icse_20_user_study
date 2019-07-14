@Override public void queueInput(ByteBuffer inputBuffer){
  boolean isInput32Bit=sourceEncoding == C.ENCODING_PCM_32BIT;
  int position=inputBuffer.position();
  int limit=inputBuffer.limit();
  int size=limit - position;
  int resampledSize=isInput32Bit ? size : (size / 3) * 4;
  if (buffer.capacity() < resampledSize) {
    buffer=ByteBuffer.allocateDirect(resampledSize).order(ByteOrder.nativeOrder());
  }
 else {
    buffer.clear();
  }
  if (isInput32Bit) {
    for (int i=position; i < limit; i+=4) {
      int pcm32BitInteger=(inputBuffer.get(i) & 0xFF) | ((inputBuffer.get(i + 1) & 0xFF) << 8) | ((inputBuffer.get(i + 2) & 0xFF) << 16) | ((inputBuffer.get(i + 3) & 0xFF) << 24);
      writePcm32BitFloat(pcm32BitInteger,buffer);
    }
  }
 else {
    for (int i=position; i < limit; i+=3) {
      int pcm32BitInteger=((inputBuffer.get(i) & 0xFF) << 8) | ((inputBuffer.get(i + 1) & 0xFF) << 16) | ((inputBuffer.get(i + 2) & 0xFF) << 24);
      writePcm32BitFloat(pcm32BitInteger,buffer);
    }
  }
  inputBuffer.position(inputBuffer.limit());
  buffer.flip();
  outputBuffer=buffer;
}
