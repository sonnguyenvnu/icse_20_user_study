public void releaseInputBuffer(byte[] buffer){
  if (inputBuffer == null || (buffer != null && buffer.length > inputBuffer.length)) {
    inputBuffer=buffer;
  }
}
