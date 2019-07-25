/** 
 * Creates a new  {@link ETag} for the given {@link String} value. Falls back to {@link #NO_ETAG} in case{@literal null} is provided.
 * @param value the source ETag value, must not be {@literal null}.
 * @return
 */
public static ETag from(String value){
  return new ETag(value);
}
