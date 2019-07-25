/** 
 * Adds a field to run the multi match against.
 */
public MultiMatchQueryBuilder field(String field){
  if (Strings.isEmpty(field)) {
    throw new IllegalArgumentException("supplied field is null or empty.");
  }
  this.fieldsBoosts.put(field,AbstractQueryBuilder.DEFAULT_BOOST);
  return this;
}
