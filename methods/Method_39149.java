/** 
 * Splits action path to chunks.
 */
public static String[] splitActionPath(final String actionPath){
  return StringUtil.splitc(actionPath.substring(1),'/');
}
