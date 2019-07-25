/** 
 * lexicographically compares two UTF-8 binary streams by code points, assumes both are of equal length
 */
static int compare(Iterator<ByteBuffer> a,Iterator<ByteBuffer> b){
  ByteBuffer x=a.next();
  ByteBuffer y=b.next();
  for (; ; ) {
    int len=Math.min(x.remaining(),y.remaining());
    for (int k=0; k < len; k++) {
      byte bx=x.get();
      byte by=y.get();
      if (bx != by) {
        return (bx & 0xFF) - (by & 0xFF);
      }
    }
    if (!x.hasRemaining()) {
      if (!a.hasNext()) {
        break;
      }
      x=a.next();
    }
    if (!y.hasRemaining()) {
      y=b.next();
    }
  }
  return 0;
}
