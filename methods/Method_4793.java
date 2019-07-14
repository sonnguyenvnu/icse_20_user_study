/** 
 * Attempts to locate the start of the next frame header. <p> If a frame header is located then the state is changed to  {@link #STATE_READING_HEADER}, the first two bytes of the header are written into  {@link #headerScratch}, and the position of the source is advanced to the byte that immediately follows these two bytes. <p> If a frame header is not located then the position of the source is advanced to the limit, and the method should be called again with the next source to continue the search.
 * @param source The source from which to read.
 */
private void findHeader(ParsableByteArray source){
  byte[] data=source.data;
  int startOffset=source.getPosition();
  int endOffset=source.limit();
  for (int i=startOffset; i < endOffset; i++) {
    boolean byteIsFF=(data[i] & 0xFF) == 0xFF;
    boolean found=lastByteWasFF && (data[i] & 0xE0) == 0xE0;
    lastByteWasFF=byteIsFF;
    if (found) {
      source.setPosition(i + 1);
      lastByteWasFF=false;
      headerScratch.data[1]=data[i];
      frameBytesRead=2;
      state=STATE_READING_HEADER;
      return;
    }
  }
  source.setPosition(endOffset);
}
