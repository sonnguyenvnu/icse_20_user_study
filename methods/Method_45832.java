public void releaseOutputBuffer(byte[] buffer){
  if (outputBuffer == null || (buffer != null && buffer.length > outputBuffer.length)) {
    outputBuffer=buffer;
  }
}
