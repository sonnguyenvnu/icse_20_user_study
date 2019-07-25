/** 
 * Pad the rest of the block with zeros and flush. pad(8) flushes the last unfinished byte. The underlying OutputStream will be flushed.
 * @param width The size of the block to pad in bits. This will typically be 8, 16, 32, 64, 128, 256, etc.
 * @throws IOException
 */
public void pad(int width) throws IOException {
  int gap=(int)this.nrBits % width;
  if (gap < 0) {
    gap+=width;
  }
  if (gap != 0) {
    int padding=width - gap;
    while (padding > 0) {
      this.zero();
      padding-=1;
    }
  }
  this.out.flush();
}
