/** 
 * ?????????????
 * @param subStr
 * @return
 */
public static String join(String... subStrs){
  if (null == subStrs || 0 == subStrs.length) {
    return EMPTY_STRING;
  }
  StringBuilder sb=new StringBuilder();
  for (  String subStr : subStrs) {
    sb.append(subStr).append(BaseConstant.WORD_SEPARATOR);
  }
  String sbStr=sb.toString();
  if (sbStr.endsWith(BaseConstant.WORD_SEPARATOR)) {
    sbStr=StringUtil.replaceLast(sbStr,BaseConstant.WORD_SEPARATOR,"");
  }
  return sbStr;
}
