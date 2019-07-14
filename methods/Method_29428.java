/** 
 * ?????????????????????
 * @param originalInt
 * @param defaultInt ??Integer
 * @return
 */
public static Integer defaultIfError(Integer originalStr,Integer defaultInt){
  try {
    return Integer.valueOf(originalStr);
  }
 catch (  Exception e) {
    return defaultInt;
  }
}
