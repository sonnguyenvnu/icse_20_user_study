/** 
 * Reads data from input stream into byte array and stores file size.
 */
@Override public void processStream() throws IOException {
  FastByteArrayOutputStream out=new FastByteArrayOutputStream();
  size=0;
  if (maxFileSize == -1) {
    size+=input.copyAll(out);
  }
 else {
    size+=input.copyMax(out,maxFileSize + 1);
    if (size > maxFileSize) {
      fileTooBig=true;
      valid=false;
      input.skipToBoundary();
      return;
    }
  }
  data=out.toByteArray();
  size=data.length;
  valid=true;
}
