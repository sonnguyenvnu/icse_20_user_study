/** 
 * Check that the rest of the block has been padded with zeroes.
 * @param width The size of the block to pad in bits. This will typically be 8, 16, 32, 64, 128, 256, etc.
 * @return true if the block was zero padded, or false if the the paddingcontains any one bits.
 * @throws IOException
 */
public boolean pad(int width) throws IOException {
  boolean result=true;
  int gap=(int)this.nrBits % width;
  if (gap < 0) {
    gap+=width;
  }
  if (gap != 0) {
    int padding=width - gap;
    while (padding > 0) {
      if (bit()) {
        result=false;
      }
      padding-=1;
    }
  }
  return result;
}
