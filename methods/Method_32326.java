/** 
 * Internal method to create a DateTimePrinter instance using all the appended elements. <p> Most applications will not use this method. If you want a printer in an application, call  {@link #toFormatter()}and just use the printing API. <p> Subsequent changes to this builder do not affect the returned printer.
 * @throws UnsupportedOperationException if printing is not supported
 */
public DateTimePrinter toPrinter(){
  Object f=getFormatter();
  if (isPrinter(f)) {
    InternalPrinter ip=(InternalPrinter)f;
    return InternalPrinterDateTimePrinter.of(ip);
  }
  throw new UnsupportedOperationException("Printing is not supported");
}
