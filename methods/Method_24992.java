/** 
 * Find byte index separating header from body. It must be the last byte of the first two sequential new lines.
 */
private int findHeaderEnd(final byte[] buf,int rlen){
  int splitbyte=0;
  while (splitbyte + 1 < rlen) {
    if (buf[splitbyte] == '\r' && buf[splitbyte + 1] == '\n' && splitbyte + 3 < rlen && buf[splitbyte + 2] == '\r' && buf[splitbyte + 3] == '\n') {
      return splitbyte + 4;
    }
    if (buf[splitbyte] == '\n' && buf[splitbyte + 1] == '\n') {
      return splitbyte + 2;
    }
    splitbyte++;
  }
  return 0;
}
