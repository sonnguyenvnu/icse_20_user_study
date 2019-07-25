/** 
 * Adds a query that <b>must</b> appear in the matching documents and will contribute to scoring. No <tt>null</tt> value allowed.
 */
public BoolQueryBuilder must(QueryBuilder queryBuilder){
  if (queryBuilder == null) {
    throw new IllegalArgumentException("inner bool query clause cannot be null");
  }
  mustClauses.add(queryBuilder);
  return this;
}
