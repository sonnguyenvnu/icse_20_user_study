/** 
 * Lookups for object reference and throws an exception if reference doesn't exist.
 */
public Object lookupObject(final String ref){
  Object value=getObjectReference(ref);
  if (value == null) {
    throw new DbSqlBuilderException("Invalid object reference: " + ref);
  }
  return value;
}
