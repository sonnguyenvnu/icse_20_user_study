/** 
 * parse the feed name from a full feed name (category.feed)
 */
public static String feed(String name){
  return StringUtils.trim(StringUtils.substringAfterLast(name,"."));
}
