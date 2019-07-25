/** 
 * This method pushed back bytes from the passed in array into the pushback buffer.  The bytes from <code>b[offset]</code> to <code>b[offset + len]</code> are pushed in reverse order so that the next byte read from the stream after this operation will be <code>b[offset]</code> followed by <code>b[offset + 1]</code>, etc. <p> If the pushback buffer cannot hold all of the requested bytes, an exception is thrown.
 * @param b The byte array to be pushed back
 * @param off The index into the array where the bytes to be push start
 * @param len The number of bytes to be pushed.
 * @exception IOException If the pushback buffer is full
 */
public synchronized void unread(byte[] b,int off,int len) throws IOException {
  if (pos < len)   throw new IOException("Insufficient space in pushback buffer");
  System.arraycopy(b,off,buf,pos - len,len);
  pos-=len;
}
