/** 
 * Creates a new publisher, backed by the given data producing function. <p> As subscribers request data of the returned stream, the given function is invoked. The function returns the item to send downstream. If the function returns  {@code null}, the stream is terminated. If the function throws an exception, the stream is terminated and the error is sent downstream. <pre class="java"> {@code import ratpack.stream.Streams; import ratpack.test.exec.ExecHarness; import java.util.Arrays; import java.util.List; import static org.junit.Assert.*;}public class Example  public static void main(String... args) throws Exception { List<String> strings = ExecHarness.yieldSingle(execControl -> Streams.yield(r -> { if (r.getRequestNum() < 2) { return Long.toString(r.getRequestNum()); } else { return null; } }).toList() ).getValue(); assertEquals(Arrays.asList("0", "1"), strings); } } }</pre> <p> If the value producing function is asynchronous, use  {@link #flatYield(Function)}.
 * @param producer the data source
 * @param < T > the type of item emitted
 * @see #flatYield
 * @return a publisher backed by the given producer
 */
public static <T>TransformablePublisher<T> yield(Function<? super YieldRequest,? extends T> producer){
  return new YieldingPublisher<>(producer);
}
