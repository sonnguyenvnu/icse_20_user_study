protected void stopBufferingForStatusCode(final int statusCode){
  if (!bufferStatusCode(statusCode)) {
    disableBuffering();
  }
}
