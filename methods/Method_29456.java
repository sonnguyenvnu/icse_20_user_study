/** 
 * / ??????????????????????????????????????
 * @param originalStr "?????
 * @param maxLength 2
 * @param suffix ...
 * @return "???..."
 */
public static String subStringIfTooLong(String originalStr,int maxLength,String suffix){
  if (StringUtil.isBlank(originalStr))   return EmptyObjectConstant.EMPTY_STRING;
  if (maxLength < 0)   maxLength=0;
  if (originalStr.length() > maxLength)   return originalStr.substring(0,maxLength) + StringUtil.trimToEmpty(suffix);
  return originalStr;
}
