/** 
 * ???????????????
 * @param name ??
 * @return ?
 */
public static String extractGivenName(String name){
  if (name.length() <= 2)   return "_" + name.substring(name.length() - 1);
 else   return name.substring(name.length() - 2);
}
