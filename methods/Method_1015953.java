/** 
 * Determines whether the specified strings contains the specified string.
 * @param string  the specified string
 * @param strings the specified strings
 * @return {@code true} if the specified strings contains the specified string, returns {@code false} otherwise
 */
public static boolean contains(final String string,final String[] strings){
  if (null == strings) {
    return false;
  }
  return Arrays.stream(strings).anyMatch(str -> StringUtils.equals(string,str));
}
