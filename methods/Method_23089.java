/** 
 * Returns the current byte position of the stream.  The next write will take place starting at this offset.
 * @return a long containing the position of the stream.
 * @exception IOException if an I/O error occurs.
 */
public long getStreamPosition() throws IOException {
  return pos;
}
