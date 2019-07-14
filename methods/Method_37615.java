/** 
 * Skips to the boundary and returns total number of bytes skipped.
 */
public int skipToBoundary() throws IOException {
  int count=0;
  while (true) {
    byte b=readByte();
    count++;
    if (isBoundary(b)) {
      break;
    }
  }
  return count;
}
