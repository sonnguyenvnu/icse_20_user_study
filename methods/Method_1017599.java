/** 
 * Reads in a given number of bytes from the backend.
 * @param buf buffer to store result
 * @param off offset in buffer
 * @param siz number of bytes to read
 * @throws IOException if a data I/O error occurs
 */
public void receive(byte[] buf,int off,int siz) throws IOException {
  int s=0;
  while (s < siz) {
    int w=pgInput.read(buf,off + s,siz - s);
    if (w < 0) {
      throw new EOFException();
    }
    s+=w;
  }
}
