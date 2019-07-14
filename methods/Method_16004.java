/** 
 * ????????????
 * @param expression ??????
 * @param language   ?????
 * @return ????
 * @throws Exception ????
 * @see ExpressionUtils#analytical(String,Map,String)
 */
public static String analytical(String expression,String language) throws Exception {
  return analytical(expression,new HashMap<>(),language);
}
