/** 
 * Sets the current stream position to the desired location.  The next read will occur at this location.  The bit offset is set to 0. <p> An <code>IndexOutOfBoundsException</code> will be thrown if <code>pos</code> is smaller than the flushed position (as returned by <code>getflushedPosition</code>). <p> It is legal to seek past the end of the file; an <code>EOFException</code> will be thrown only if a read is performed.
 * @param pos a <code>long</code> containing the desired filepointer position.
 * @exception IndexOutOfBoundsException if <code>pos</code> is smallerthan the flushed position.
 * @exception IOException if any other I/O error occurs.
 */
public void seek(long pos) throws IOException {
  this.pos=(int)pos;
}
