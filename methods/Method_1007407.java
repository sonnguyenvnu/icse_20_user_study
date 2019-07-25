/** 
 * Assert a boolean expression, throwing  {@link IllegalStateException} ifthe test result is  {@code false}. <p> Call  {@link #isTrue(boolean)} if you wish to throw{@link IllegalArgumentException} on an assertion failure.<pre class="code"> Assert.state(id == null); </pre>
 * @param expression a boolean expression
 * @throws IllegalStateException if the supplied expression is  {@code false}
 */
public static void state(boolean expression){
  state(expression,"[Assertion failed] - this state invariant must be true");
}
