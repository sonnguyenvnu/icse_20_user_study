/** 
 * Get or create a case insensitive string value for the given string. The value will have the same case as the passed string.
 * @param s the string
 * @return the value
 */
public static ValueStringIgnoreCase get(String s){
  int length=s.length();
  if (length == 0) {
    return EMPTY;
  }
  ValueStringIgnoreCase obj=new ValueStringIgnoreCase(StringUtils.cache(s));
  if (length > SysProperties.OBJECT_CACHE_MAX_PER_ELEMENT_SIZE) {
    return obj;
  }
  ValueStringIgnoreCase cache=(ValueStringIgnoreCase)Value.cache(obj);
  if (cache.value.equals(s)) {
    return cache;
  }
  return obj;
}
