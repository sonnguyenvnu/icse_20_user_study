/** 
 * Converts the given string to Camel case, that is, removing all spaces, and capitalising the first letter of each word except the first. <p>The second parameter can be used to force the words to be converted to lowercase before capitalising. This can be useful if eg the first word contains several uppercase letters.
 * @param name           The string to convert
 * @param forceLowerCase Whether to force removal of all uppercase letters except on word start
 * @return The string converted to Camel case
 */
public static String toCamelCase(String name,boolean forceLowerCase){
  StringBuilder sb=new StringBuilder();
  boolean isFirst=true;
  for (  String word : name.trim().split("\\s++")) {
    String pretreated=forceLowerCase ? word.toLowerCase(Locale.ROOT) : word;
    if (isFirst) {
      sb.append(pretreated);
      isFirst=false;
    }
 else {
      sb.append(StringUtils.capitalize(pretreated));
    }
  }
  return sb.toString();
}
