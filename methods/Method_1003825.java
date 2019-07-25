/** 
 * Converts a  {@link Promise} into an {@link Observable}. <p> The returned observable emits the promise's single value if it succeeds, and emits the error (i.e. via  {@code onError()}) if it fails. <p> This method works well as a method reference to the  {@link Promise#to(ratpack.func.Function)} method.<pre class="java"> {@code import ratpack.rx.RxRatpack; import ratpack.exec.Promise; import ratpack.test.exec.ExecHarness; import static org.junit.Assert.assertEquals;}public class Example  public static String value; public static void main(String... args) throws Exception { ExecHarness.runSingle(e -> Promise.value("hello world") .to(RxRatpack::observe) .map(String::toUpperCase) .subscribe(s -> value = s) ); assertEquals("HELLO WORLD", value); } } }</pre>
 * @param promise the promise
 * @param < T > the type of value promised
 * @return an observable for the promised value
 */
public static <T>Observable<T> observe(Promise<T> promise){
  return Observable.create(subscriber -> promise.onError(subscriber::onError).then(value -> {
    subscriber.onNext(value);
    subscriber.onCompleted();
  }
));
}
