/** 
 * Checks whether printing is supported.
 * @throws UnsupportedOperationException if printing is not supported
 */
private InternalPrinter requirePrinter(){
  InternalPrinter printer=iPrinter;
  if (printer == null) {
    throw new UnsupportedOperationException("Printing not supported");
  }
  return printer;
}
