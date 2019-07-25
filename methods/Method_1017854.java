/** 
 * Parse the category name from a full feed name  (category.feed)
 */
public static String category(String name){
  return StringUtils.trim(StringUtils.substringBefore(name,"."));
}
