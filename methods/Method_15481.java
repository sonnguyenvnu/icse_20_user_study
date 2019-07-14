/** 
 * ?????
 * @param fullName
 * @return {@link #formatKey(String,boolean,boolean,boolean,boolean)} formatColon = true, formatAt = true, formatHyphen = true, firstCase = true
 */
public static String getVariableName(String fullName){
  if (isArrayKey(fullName)) {
    fullName=StringUtil.addSuffix(fullName.substring(0,fullName.length() - 2),"list");
  }
  return formatKey(fullName,true,true,true,true);
}
