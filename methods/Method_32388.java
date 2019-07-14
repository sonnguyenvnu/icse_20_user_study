/** 
 * Checks that the iso only flag is not set, throwing an exception if it is.
 * @param fields  the fields
 * @param strictISO  true if only ISO formats allowed
 * @since 1.1
 */
private static void checkNotStrictISO(Collection<DateTimeFieldType> fields,boolean strictISO){
  if (strictISO) {
    throw new IllegalArgumentException("No valid ISO8601 format for fields: " + fields);
  }
}
