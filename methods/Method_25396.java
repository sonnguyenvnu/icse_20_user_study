/** 
 * Matches a whitelisted method invocation that is known to never return null 
 */
public static Matcher<ExpressionTree> methodReturnsNonNull(){
  return anyOf(instanceMethod().onDescendantOf("java.lang.Object").named("toString"),instanceMethod().onExactClass("java.lang.String"),staticMethod().onClass("java.lang.String"),instanceMethod().onExactClass("java.util.StringTokenizer").named("nextToken"));
}
