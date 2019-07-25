/** 
 * Add a field to run the query against with a specific boost. 
 */
public SimpleQueryStringBuilder field(String field,float boost){
  if (Strings.isEmpty(field)) {
    throw new IllegalArgumentException("supplied field is null or empty");
  }
  this.fieldsAndWeights.put(field,boost);
  return this;
}
