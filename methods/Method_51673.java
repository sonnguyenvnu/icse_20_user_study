/** 
 * Returns true if the argument is null or the empty string.
 * @param value String to test
 * @return True if the argument is null or the empty string
 * @deprecated {@link StringUtils#isEmpty(CharSequence)}
 */
@Deprecated public static boolean isMissing(String value){
  return StringUtils.isEmpty(value);
}
