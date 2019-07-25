/** 
 * Repositions this stream to the position at the time the <code>mark</code> method was last called on this input stream. NB: If mark is not called we move to the beginning.
 * @see java.io.InputStream#mark(int)
 * @see java.io.IOException
 */
public synchronized void reset() throws IOException {
  checkClosed();
  try {
    if (mpos <= Integer.MAX_VALUE) {
      lo.seek((int)mpos);
    }
 else {
      lo.seek64(mpos,LargeObject.SEEK_SET);
    }
    buffer=null;
    apos=mpos;
  }
 catch (  SQLException se) {
    throw new IOException(se.toString());
  }
}
