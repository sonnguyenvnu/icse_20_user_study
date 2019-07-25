/** 
 * Converts a  {@link Promise} into a {@link Single}. <p> The returned Single emits the promise's single value if it succeeds, and emits the error (i.e. via  {@code onError()}) if it fails. <p> This method works well as a method reference to the  {@link Promise#to(ratpack.func.Function)} method.<pre class="java"> {@code import ratpack.rx2.RxRatpack; import ratpack.exec.Promise; import ratpack.test.exec.ExecHarness; import static org.junit.Assert.assertEquals;}public class Example  public static String value; public static void main(String... args) throws Exception { ExecHarness.runSingle(e -> Promise.value("hello world") .to(RxRatpack::single) .map(String::toUpperCase) .subscribe(s -> value = s) ); assertEquals("HELLO WORLD", value); } } }</pre>
 * @param promise the promise
 * @param < T >     the type of value promised
 * @return a single for the promised value
 */
public static <T>Single<T> single(Promise<T> promise){
  return Single.create(subscriber -> promise.onError(subscriber::onError).then(subscriber::onSuccess));
}
