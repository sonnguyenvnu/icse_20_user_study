/** 
 * Encodes and writes pixel data
 */
private void writePixels() throws IOException {
  LZWEncoder encoder=new LZWEncoder(width,height,indexedPixels,colorDepth);
  encoder.encode(out);
}
