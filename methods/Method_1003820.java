/** 
 * Converts a  {@link Operation} into a {@link Flux}. <p> The returned flux emits completes upon completion of the operation without emitting a value, and emits the error (i.e. via  {@code errer()}) if it fails. <pre class="java"> {@code import ratpack.reactor.ReactorRatpack; import ratpack.exec.Operation; import ratpack.test.exec.ExecHarness; <p> import public static org.junit.Assert.assertTrue; <p>}public class Example  public static boolean executed; public static void main(String... args) throws Exception { ExecHarness.runSingle(e -> Operation.of(() -> executed = true) .to(ReactorRatpack::observe) .subscribe() ); <p> assertTrue(executed); } } }</pre>
 * @param operation the operation
 * @return an observable for the operation
 */
public static Flux<Void> flux(Operation operation){
  return Flux.create(sink -> operation.onError(sink::error).then(sink::complete));
}
