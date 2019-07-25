/** 
 * Read one bit.
 * @return true if it is a 1 bit.
 */
public boolean bit() throws IOException {
  return read(1) != 0;
}
