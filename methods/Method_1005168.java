/** 
 * ??????????String
 * @param objs
 * @return
 */
public static String concate(Object... objs){
  if (objs == null || objs.length <= 0) {
    return "";
  }
  StringBuffer result=new StringBuffer();
  for (int i=0; i < objs.length; i++) {
    result.append(formatEmpty(objs[i]));
    result.append("_");
  }
  return result.toString();
}
