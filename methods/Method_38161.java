/** 
 * Detach used  {@link DbQuery}. Usually invoked by  {@link jodd.db.DbQuery#close()}.
 */
protected void detachQuery(final DbQueryBase query){
  queries.remove(query);
}
