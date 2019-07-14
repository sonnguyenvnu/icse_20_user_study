/** 
 * @param s
 * @param isEmpty <=0
 * @param trim s = trim(s);
 * @param nullable isNull(s, true) + {@link #OR} +
 * @return {@link #lengthCompare(String,String)}
 */
public static String isEmpty(String s,boolean isEmpty,boolean trim,boolean nullable){
  if (trim) {
    s=trim(s);
  }
  return (nullable ? isNull(s,true) + OR : "") + lengthCompare(s,(isEmpty ? "<=" : ">") + "0");
}
