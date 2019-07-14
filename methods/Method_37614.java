/** 
 * Reads boundary from the input stream.
 */
public byte[] readBoundary() throws IOException {
  FastByteArrayOutputStream boundaryOutput=new FastByteArrayOutputStream();
  byte b;
  while ((b=readByte()) <= ' ') {
  }
  boundaryOutput.write(b);
  while ((b=readByte()) != '\r') {
    boundaryOutput.write(b);
  }
  if (boundaryOutput.size() == 0) {
    throw new IOException("Problems with parsing request: invalid boundary");
  }
  skipBytes(1);
  boundary=new byte[boundaryOutput.size() + 2];
  System.arraycopy(boundaryOutput.toByteArray(),0,boundary,2,boundary.length - 2);
  boundary[0]='\r';
  boundary[1]='\n';
  return boundary;
}
