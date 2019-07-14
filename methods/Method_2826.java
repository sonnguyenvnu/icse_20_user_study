/** 
 * ????????????
 * @param name
 * @param delimiter
 * @return
 */
public static String getSuffix(String name,String delimiter){
  return name.substring(name.lastIndexOf(delimiter) + 1);
}
