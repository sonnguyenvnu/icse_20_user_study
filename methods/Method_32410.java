/** 
 * Checks whether printing is supported.
 * @throws UnsupportedOperationException if printing is not supported
 */
private void checkPrinter(){
  if (iPrinter == null) {
    throw new UnsupportedOperationException("Printing not supported");
  }
}
