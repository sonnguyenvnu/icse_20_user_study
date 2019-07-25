/** 
 * Combine a string URI with a prefix and a suffix.
 */
public static String combine(String prefix,String suffix){
  if (!prefix.endsWith("" + FORWARD_SLASH_CHAR) && !suffix.startsWith("" + FORWARD_SLASH_CHAR))   return prefix + FORWARD_SLASH_CHAR + suffix;
 else   if ((!prefix.endsWith("" + FORWARD_SLASH_CHAR) && suffix.startsWith("" + FORWARD_SLASH_CHAR) || (prefix.endsWith("" + FORWARD_SLASH_CHAR) && !suffix.startsWith("" + FORWARD_SLASH_CHAR))))   return prefix + suffix;
 else   return "";
}
