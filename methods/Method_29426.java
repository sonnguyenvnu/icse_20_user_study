/** 
 * ???0,??????
 * @param originalInt
 * @param defaultInt ??Integer
 * @return
 */
public static Integer defaultIfZero(Integer originalInt,Integer defaultInt){
  if (0 == originalInt) {
    return defaultInt;
  }
  return originalInt;
}
