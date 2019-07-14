/** 
 * Matches calls to the method  {@link Object#equals(Object)} or any override of that method. 
 */
public static Matcher<ExpressionTree> instanceEqualsInvocation(){
  return INSTANCE_EQUALS;
}
