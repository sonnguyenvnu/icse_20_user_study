/** 
 * ???0,??????
 * @param originalInt
 * @param defaultInt ??Integer
 * @return
 */
public static Integer defaultIfError(String originalStr,Integer defaultInt){
  try {
    return Integer.parseInt(StringUtil.trimToEmpty(originalStr));
  }
 catch (  Exception e) {
    return defaultInt;
  }
}
