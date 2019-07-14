/** 
 * TODO optimize
 * @param expression expression
 * @return true if wrapper type
 */
public static boolean isWrapperType(ASTPrimaryExpression expression){
  return TypeHelper.isA(expression,Integer.class) || TypeHelper.isA(expression,Long.class) || TypeHelper.isA(expression,Boolean.class) || TypeHelper.isA(expression,Byte.class) || TypeHelper.isA(expression,Double.class) || TypeHelper.isA(expression,Short.class) || TypeHelper.isA(expression,Float.class) || TypeHelper.isA(expression,Character.class);
}
