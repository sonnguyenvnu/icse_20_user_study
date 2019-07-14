/** 
 * ????????
 * @param e
 * @return
 */
public static String exceptionToString(Exception e){
  StringWriter sw=new StringWriter();
  PrintWriter pw=new PrintWriter(sw);
  e.printStackTrace(pw);
  return sw.toString();
}
