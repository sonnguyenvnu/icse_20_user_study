/** 
 * Adds a clause that <i>should</i> be matched by the returned documents. For a boolean query with no <tt>MUST</tt> clauses one or more <code>SHOULD</code> clauses must match a document for the BooleanQuery to match. No <tt>null</tt> value allowed.
 * @see #minimumShouldMatch(int)
 */
public BoolQueryBuilder should(QueryBuilder queryBuilder){
  if (queryBuilder == null) {
    throw new IllegalArgumentException("inner bool query clause cannot be null");
  }
  shouldClauses.add(queryBuilder);
  return this;
}
