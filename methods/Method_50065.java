/** 
 * Writes Image Descriptor
 */
private void writeImageDesc(int x,int y) throws IOException {
  out.write(0x2c);
  writeShort(x);
  writeShort(y);
  writeShort(width);
  writeShort(height);
  if (firstFrame) {
    out.write(0);
  }
 else {
    out.write(0x80 | 0 | 0 | 0 | palSize);
  }
}
