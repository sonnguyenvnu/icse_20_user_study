/** 
 * A variant of  {@link #fork} that allows access to the registry of the forked execution inside an {@link Action}. <p> This allows the insertion of objects via  {@link RegistrySpec#add} that will be available to the forked observable.<p> You do not have access to the original execution inside the  {@link Action}. <pre class="java"> {@code import ratpack.exec.Execution; import ratpack.registry.RegistrySpec; import ratpack.rx.RxRatpack; import ratpack.test.exec.ExecHarness; import rx.Observable; import static org.junit.Assert.assertEquals;}public class Example  public static void main(String[] args) throws Exception { RxRatpack.initialize(); try (ExecHarness execHarness = ExecHarness.harness(6)) { String concatenatedResult = execHarness.yield(execution -> { Observable<String> notYetForked = Observable.just("foo") .map((value) -> value + Execution.current().get(String.class)); Observable<String> forkedObservable = RxRatpack.fork( notYetForked, (RegistrySpec registrySpec) -> registrySpec.add("bar") ); return RxRatpack.promiseSingle(forkedObservable); }).getValueOrThrow(); assertEquals(concatenatedResult, "foobar"); } } } }</pre>
 * @param observable the observable sequence to execute on a different compute thread
 * @param doWithRegistrySpec an Action where objects can be inserted into the registry of the forked execution
 * @param < T > the element type
 * @return an observable on the compute thread that <code>fork</code> was called from
 * @throws Exception
 * @since 1.4
 * @see #fork(Observable)
 */
public static <T>Observable<T> fork(Observable<T> observable,Action<? super RegistrySpec> doWithRegistrySpec) throws Exception {
  return observeEach(promise(observable).fork(execSpec -> execSpec.register(doWithRegistrySpec)));
}
