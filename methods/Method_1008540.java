/** 
 * Add a field to run the query against. 
 */
public SimpleQueryStringBuilder field(String field){
  if (Strings.isEmpty(field)) {
    throw new IllegalArgumentException("supplied field is null or empty");
  }
  this.fieldsAndWeights.put(field,AbstractQueryBuilder.DEFAULT_BOOST);
  return this;
}
