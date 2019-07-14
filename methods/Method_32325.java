/** 
 * Constructs a DateTimeFormatter using all the appended elements. <p> This is the main method used by applications at the end of the build process to create a usable formatter. <p> Subsequent changes to this builder do not affect the returned formatter. <p> The returned formatter may not support both printing and parsing. The methods  {@link DateTimeFormatter#isPrinter()} and{@link DateTimeFormatter#isParser()} will help you determine the stateof the formatter.
 * @throws UnsupportedOperationException if neither printing nor parsing is supported
 */
public DateTimeFormatter toFormatter(){
  Object f=getFormatter();
  InternalPrinter printer=null;
  if (isPrinter(f)) {
    printer=(InternalPrinter)f;
  }
  InternalParser parser=null;
  if (isParser(f)) {
    parser=(InternalParser)f;
  }
  if (printer != null || parser != null) {
    return new DateTimeFormatter(printer,parser);
  }
  throw new UnsupportedOperationException("Both printing and parsing not supported");
}
