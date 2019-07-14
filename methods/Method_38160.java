/** 
 * Attaches a new  {@link DbQuery}. May be invoked both inside and outside of transaction.
 */
protected void attachQuery(final DbQueryBase query){
  assertSessionIsOpen();
  openConnectionForQuery();
  queries.add(query);
}
