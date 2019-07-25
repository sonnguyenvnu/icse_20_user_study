/** 
 * Replaces  {@code this} promise with the provided promise for downstream subscribers.<p> This is simply a more convenient form of  {@link #flatMap(Function)}, where the given promise is returned. This method can be used when a subsequent operation on a promise isn't dependent on the actual promised value. <p> If the upstream promise fails, its error will propagate downstream and the given promise will never be subscribed to. <pre class="java"> {@code import ratpack.test.exec.ExecHarness; import ratpack.exec.ExecResult; import ratpack.exec.Promise; import static org.junit.Assert.assertEquals;}public class Example  private static String value; public static void main(String... args) throws Exception { ExecResult<String> result = ExecHarness.yieldSingle(c -> Promise.value("foo") .next(v -> value = v) .replace(Promise.value("bar")) ); assertEquals("bar", result.getValue()); assertEquals("foo", value); } } }</pre>
 * @param next the promise to replace {@code this} with
 * @param < O > the type of the value of the replacement promise
 * @return a promise
 * @since 1.1
 */
default <O>Promise<O> replace(Promise<O> next){
  return flatMap(in -> next);
}
