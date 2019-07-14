/** 
 * ??long???string
 * @param x
 * @return
 */
public static String long2String(long x){
  char[] cArray=long2char(x);
  StringBuilder sbResult=new StringBuilder(cArray.length);
  for (  char c : cArray) {
    sbResult.append(c);
  }
  return sbResult.toString();
}
