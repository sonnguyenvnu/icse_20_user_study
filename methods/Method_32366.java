/** 
 * Computes the parsed datetime by setting the saved fields. This method is idempotent, but it is not thread-safe.
 * @param resetFields false by default, but when true, unsaved field values are cleared
 * @return milliseconds since 1970-01-01T00:00:00Z
 * @throws IllegalArgumentException if any field is out of range
 */
public long computeMillis(boolean resetFields){
  return computeMillis(resetFields,(CharSequence)null);
}
