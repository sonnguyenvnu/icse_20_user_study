/** 
 * Writes out a <code>byte</code> to the underlying output stream as a 1-byte value. If no exception is thrown, the counter <code>written</code> is incremented by <code>1</code>.
 * @param v   a <code>byte</code> value to be written.
 * @exception IOException  if an I/O error occurs.
 * @see java.io.FilterOutputStream#out
 */
public final void writeByte(int v) throws IOException {
  out.write(v);
  incCount(1);
}
