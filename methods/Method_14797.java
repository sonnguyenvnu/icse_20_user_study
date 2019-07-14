/** 
 * ????
 * @param s
 * @return
 */
public static String toLowerCase(String s,boolean trim){
  s=trim ? getTrimedString(s) : getString(s);
  return s.toLowerCase();
}
