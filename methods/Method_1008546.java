/** 
 * Set the query that will filter the source. Just a convenience method for easy chaining.
 */
public Self filter(QueryBuilder filter){
  source.setQuery(filter);
  return self();
}
