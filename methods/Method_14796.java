/** 
 * ????
 * @param s
 * @param trim
 * @return
 */
public static String toUpperCase(String s,boolean trim){
  s=trim ? getTrimedString(s) : getString(s);
  return s.toUpperCase();
}
