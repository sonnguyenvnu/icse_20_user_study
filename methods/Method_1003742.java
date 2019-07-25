/** 
 * Executes the provided, potentially asynchronous,  {@link Action} with the promised value as input.<p> This method can be used when needing to perform an action with the promised value, without substituting the promised value. That is, the exact same object provided to the given action will be propagated downstream. <p> The given action is executed within an  {@link Operation}, allowing it to perform asynchronous work. <pre class="java"> {@code import ratpack.test.exec.ExecHarness; import ratpack.exec.ExecResult; import ratpack.exec.Promise; import com.google.common.collect.Lists; import java.util.concurrent.TimeUnit; import java.util.Arrays; import java.util.List; import static org.junit.Assert.assertEquals;}public class Example  public static void main(String... args) throws Exception { List<String> events = Lists.newLinkedList(); ExecHarness.runSingle(c -> Promise.value("foo") .next(v -> Promise.value(v) // may be async .map(String::toUpperCase) .then(events::add) ) .then(events::add) ); assertEquals(Arrays.asList("FOO", "foo"), events); } } }</pre>
 * @param action the action to execute with the promised value
 * @return a promise for the original value
 * @see #nextOp(Function)
 * @since 1.1
 */
default Promise<T> next(@NonBlocking Action<? super T> action){
  return nextOp(v -> Operation.of(() -> action.execute(v)));
}
