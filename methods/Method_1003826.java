/** 
 * Converts a  {@link Operation} into an {@link Observable}. <p> The returned observable emits completes upon completion of the operation without emitting a value, and emits the error (i.e. via  {@code onError()}) if it fails. <pre class="java"> {@code import ratpack.rx.RxRatpack; import ratpack.exec.Operation; import ratpack.test.exec.ExecHarness; import static org.junit.Assert.assertTrue;}public class Example  public static boolean executed; public static void main(String... args) throws Exception { ExecHarness.runSingle(e -> Operation.of(() -> executed = true) .to(RxRatpack::observe) .subscribe() ); assertTrue(executed); } } }</pre>
 * @param operation the operation
 * @return an observable for the operation
 */
public static Observable<Void> observe(Operation operation){
  return Observable.create(subscriber -> operation.onError(subscriber::onError).then(subscriber::onCompleted));
}
