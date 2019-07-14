/** 
 * Match method name to provided  {@link jodd.util.Wildcard} pattern.
 */
default boolean matchMethodName(final String wildcard){
  return Wildcard.match(getMethodName(),wildcard);
}
