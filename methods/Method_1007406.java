/** 
 * Assert a boolean expression, throwing  {@code IllegalStateException} ifthe test result is  {@code false}. Call isTrue if you wish to throw IllegalArgumentException on an assertion failure. <pre class="code"> Assert.state(id == null, "The id property must not already be initialized"); </pre>
 * @param expression a boolean expression
 * @param message the exception message to use if the assertion fails
 * @throws IllegalStateException if expression is  {@code false}
 */
public static void state(boolean expression,String message){
  if (!expression) {
    throw new IllegalStateException(message);
  }
}
