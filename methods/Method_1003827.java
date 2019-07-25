/** 
 * Converts an  {@link Observable} into a {@link Publisher}, for all of the observable's items. <p> This method can be used to simply adapt an observable to a ReactiveStreams publisher. It is sometimes more convenient to use  {@link #publisher(Observable.OnSubscribe)} over this method.<pre class="java"> {@code import ratpack.rx.RxRatpack; import ratpack.stream.Streams; import ratpack.test.exec.ExecHarness; import rx.Observable; import java.util.List; import static org.junit.Assert.assertEquals;}public class Example  static class AsyncService { public <T> Observable<T> observe(final T value) { return Observable.create(subscriber -> new Thread(() -> { subscriber.onNext(value); subscriber.onCompleted(); }).start() ); } } public static void main(String[] args) throws Throwable { List<String> result = ExecHarness.yieldSingle(execution -> RxRatpack.publisher(new AsyncService().observe("foo")).toList() ).getValue(); assertEquals("foo", result.get(0)); } } }</pre>
 * @param observable the observable
 * @param < T > the type of the value observed
 * @return a ReactiveStreams publisher containing each value of the observable
 */
public static <T>TransformablePublisher<T> publisher(Observable<T> observable){
  return Streams.transformable(RxReactiveStreams.toPublisher(observable));
}
