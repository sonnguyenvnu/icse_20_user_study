/** 
 * Match class name to provided  {@link jodd.util.Wildcard} pattern.
 */
default boolean matchClassName(final String wildcard){
  return Wildcard.match(getClassname(),wildcard);
}
