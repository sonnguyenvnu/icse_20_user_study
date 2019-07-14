/** 
 * Given a base key path and count, find the appropriate string resource and replace each key found in the string resource with its corresponding value. For example, given a base key of `foo`, a count of 0 would give the string resource `foo_zero`, a count of 1 `foo_one`, and so on. This particular version is for strings that have no replaceable sections
 */
public @NonNull String format(final @NonNull String baseKeyPath,final int count){
  return stringFromKeyPath(baseKeyPath,keyPathComponentForCount(count));
}
