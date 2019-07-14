/** 
 * <p> Replace the table prefix in a query by replacing any occurrences of "{0}" with the table prefix. </p>
 * @param query the unsubstitued query
 * @return the query, with proper table prefix substituted
 */
protected final String rtp(String query){
  return Util.rtp(query,tablePrefix,getSchedulerNameLiteral());
}
