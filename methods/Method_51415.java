/** 
 * Return the value of `method' as a string that can be easily recognized and parsed when we see it again.
 * @param method the method to convert
 * @return the string value
 */
private static String asStringFor(Method method){
  StringBuilder sb=new StringBuilder();
  asStringOn(method,sb);
  return sb.toString();
}
