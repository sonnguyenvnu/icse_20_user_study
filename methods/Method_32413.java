/** 
 * Internal method to create a PeriodParser instance using all the appended elements. <p> Most applications will not use this method. If you want a printer in an application, call  {@link #toFormatter()}and just use the printing API. <p> Subsequent changes to this builder do not affect the returned parser.
 * @return the newly created parser, null if builder cannot create a parser
 */
public PeriodParser toParser(){
  if (iNotParser) {
    return null;
  }
  return toFormatter().getParser();
}
