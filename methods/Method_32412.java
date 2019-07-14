/** 
 * Internal method to create a PeriodPrinter instance using all the appended elements. <p> Most applications will not use this method. If you want a printer in an application, call  {@link #toFormatter()}and just use the printing API. <p> Subsequent changes to this builder do not affect the returned printer.
 * @return the newly created printer, null if builder cannot create a printer
 */
public PeriodPrinter toPrinter(){
  if (iNotPrinter) {
    return null;
  }
  return toFormatter().getPrinter();
}
