/** 
 * ?Throwable????????.
 * @param cause ???????
 * @return ?????????
 */
public static String transform(final Throwable cause){
  if (null == cause) {
    return "";
  }
  StringWriter result=new StringWriter();
  try (PrintWriter writer=new PrintWriter(result)){
    cause.printStackTrace(writer);
  }
   return result.toString();
}
