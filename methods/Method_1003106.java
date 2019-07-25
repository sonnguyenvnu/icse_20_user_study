/** 
 * [Not supported] Searches a pattern and return the position.
 * @param pattern the pattern to search
 * @param start the index, the first byte is at position 1
 * @return the position (first byte is at position 1), or -1 for not found
 */
@Override public long position(byte[] pattern,long start) throws SQLException {
  if (isDebugEnabled()) {
    debugCode("position(" + quoteBytes(pattern) + ", " + start + ");");
  }
  if (Constants.BLOB_SEARCH) {
    try {
      checkReadable();
      if (pattern == null) {
        return -1;
      }
      if (pattern.length == 0) {
        return 1;
      }
      BufferedInputStream in=new BufferedInputStream(value.getInputStream());
      IOUtils.skipFully(in,start - 1);
      int pos=0;
      int patternPos=0;
      while (true) {
        int x=in.read();
        if (x < 0) {
          break;
        }
        if (x == (pattern[patternPos] & 0xff)) {
          if (patternPos == 0) {
            in.mark(pattern.length);
          }
          if (patternPos == pattern.length) {
            return pos - patternPos;
          }
          patternPos++;
        }
 else {
          if (patternPos > 0) {
            in.reset();
            pos-=patternPos;
          }
        }
        pos++;
      }
      return -1;
    }
 catch (    Exception e) {
      throw logAndConvert(e);
    }
  }
  throw unsupported("LOB search");
}
