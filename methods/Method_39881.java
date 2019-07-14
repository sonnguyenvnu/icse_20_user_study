public void flushToGZip() throws IOException {
  if (bufferCount > 0) {
    writeToGZip(buffer,0,bufferCount);
    bufferCount=0;
  }
}
