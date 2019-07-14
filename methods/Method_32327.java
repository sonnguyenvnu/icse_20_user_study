/** 
 * Internal method to create a DateTimeParser instance using all the appended elements. <p> Most applications will not use this method. If you want a parser in an application, call  {@link #toFormatter()}and just use the parsing API. <p> Subsequent changes to this builder do not affect the returned parser.
 * @throws UnsupportedOperationException if parsing is not supported
 */
public DateTimeParser toParser(){
  Object f=getFormatter();
  if (isParser(f)) {
    InternalParser ip=(InternalParser)f;
    return InternalParserDateTimeParser.of(ip);
  }
  throw new UnsupportedOperationException("Parsing is not supported");
}
