/** 
 * Replace the tags in the HTML page with the given settings.
 * @param page the HTML page
 * @param settings the settings
 * @return the converted page
 */
public static String parse(String page,Map<String,Object> settings){
  PageParser block=new PageParser(page,settings,0);
  return block.replaceTags();
}
