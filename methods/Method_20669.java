/** 
 * Given a base key path and count, find the appropriate string resource and replace each key found in the string resource with its corresponding value. For example, given a base key of `foo`, a count of 0 would give the string resource `foo_zero`, a count of 1 `foo_one`, and so on.
 */
public @NonNull String format(final @NonNull String baseKeyPath,final int count,final @NonNull String key1,final @Nullable String value1,final @NonNull String key2,final @Nullable String value2){
  final String string=stringFromKeyPath(baseKeyPath,keyPathComponentForCount(count));
  return format(string,key1,value1,key2,value2);
}
