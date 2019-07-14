/** 
 * ????????? name => name; name:alias => alias
 * @param fullName name ? name:alias
 * @return {@link #formatKey(String,boolean,boolean,boolean,boolean)} formatColon = false, formatAt = true, formatHyphen = false, firstCase = false
 */
public static String formatOtherKey(String fullName){
  return formatKey(fullName,false,true,false,false);
}
