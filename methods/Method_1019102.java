/** 
 * Trim the text to a last section, if needed.
 * @param item Internal class name.
 * @param delim Delimter to trim to.
 * @return Simple name.
 */
public static String trim(String item,String delim){
  return item.indexOf(delim) > 0 ? item.substring(item.lastIndexOf(delim) + 1) : item;
}
