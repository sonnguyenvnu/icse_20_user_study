/** 
 * Converts a  {@link Promise} into an {@link Flux}. <p> The returned observable emits the promise's single value if it succeeds, and emits the error (i.e. via  {@code onError()}) if it fails. <p> This method works well as a method reference to the  {@link Promise#to(ratpack.func.Function)} method.<pre class="java"> {@code import ratpack.reactor.ReactorRatpack; import ratpack.exec.Promise; import ratpack.test.exec.ExecHarness; <p> import public static org.junit.Assert.assertEquals; <p>}public class Example  public static String value; public static void main(String... args) throws Exception { ExecHarness.runSingle(e -> Promise.value("hello world") .to(ReactorRatpack::observe) .map(String::toUpperCase) .subscribe(s -> value = s) ); <p> assertEquals("HELLO WORLD", value); } } }</pre>
 * @param promise the promise
 * @param < T >     the type of value promised
 * @return an observable for the promised value
 */
public static <T>Flux<T> flux(Promise<T> promise){
  return Flux.create(subscriber -> promise.onError(subscriber::error).then(value -> {
    subscriber.next(value);
    subscriber.complete();
  }
));
}
