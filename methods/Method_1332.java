/** 
 * Checks if there is some data left in the buffer. If not but buffered stream still has some data to be read, then more data is buffered.
 * @return false if and only if there is no more data and underlying input stream has no more datato be read
 * @throws IOException
 */
private boolean ensureDataInBuffer() throws IOException {
  if (mBufferOffset < mBufferedSize) {
    return true;
  }
  final int readData=mInputStream.read(mByteArray);
  if (readData <= 0) {
    return false;
  }
  mBufferedSize=readData;
  mBufferOffset=0;
  return true;
}
