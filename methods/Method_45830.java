public void releaseEncodeBuffer(byte[] buffer){
  if (encodingBuffer == null || buffer.length > encodingBuffer.length) {
    encodingBuffer=buffer;
  }
}
