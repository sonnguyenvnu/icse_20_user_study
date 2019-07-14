/** 
 * ????,??????<br>
 * @param originalInt
 * @param defaultInt ??Integer
 * @return originalInt if originalInt>0, return defaultInt if originalInt<=0
 */
public static Integer defaultIfSmallerThan0(Integer originalInt,Integer defaultInt){
  if (0 >= originalInt) {
    return defaultInt;
  }
  return originalInt;
}
