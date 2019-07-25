/** 
 * Makes the stream readable again after a  {@link #close()}.
 * @see #close()
 */
public void reopen() throws IOException {
  if (!fileChannel.isOpen())   throw new IOException("This " + getClass().getSimpleName() + " is closed");
  position=0;
}
