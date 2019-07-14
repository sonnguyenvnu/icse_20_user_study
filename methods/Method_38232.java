/** 
 * Specifies hints for the query. Provided string is split on ',' separator.
 */
public DbOomQuery withHints(final String hint){
  this.hints=StringUtil.splitc(hint,',');
  return this;
}
