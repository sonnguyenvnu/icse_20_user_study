/** 
 * Allows the promised value to be handled specially if it meets the given predicate, instead of being handled by the promise subscriber. <p> This is typically used for validating values, centrally. <pre class="java"> {@code import com.google.common.collect.Lists; import ratpack.test.exec.ExecHarness; import ratpack.exec.ExecResult; import ratpack.exec.Promise; import java.util.List; import static org.junit.Assert.*;}public class Example  public static ExecResult<Integer> yield(int i, List<Integer> collector) throws Exception { return ExecHarness.yieldSingle(c -> Promise.value(i) .route(v -> v > 5, collector::add) ); } public static void main(String... args) throws Exception { List<Integer> routed = Lists.newLinkedList(); ExecResult<Integer> result1 = yield(1, routed); assertEquals(new Integer(1), result1.getValue()); assertFalse(result1.isComplete()); // false because promise returned a value before the execution completed assertTrue(routed.isEmpty()); ExecResult<Integer> result10 = yield(10, routed); assertNull(result10.getValue()); assertTrue(result10.isComplete()); // true because the execution completed before the promised value was returned (i.e. it was routed) assertTrue(routed.contains(10)); } } }</pre> <p> Be careful about using this where the eventual promise subscriber is unlikely to know that the promise will routed as it can be surprising when neither the promised value nor an error appears. <p> It can be useful at the handler layer to provide common validation. <pre class="java"> {@code import ratpack.exec.Promise; import ratpack.handling.Context; import ratpack.test.embed.EmbeddedApp; import static org.junit.Assert.assertEquals;}public class Example  public static Promise<Integer> getAge(Context ctx) { return Promise.value(10) .route( i -> i < 21, i -> ctx.render(i + " is too young to be here!") ); } public static void main(String... args) throws Exception { EmbeddedApp.fromHandler(ctx -> getAge(ctx).then(age -> ctx.render("welcome!")) ).test(httpClient -> { assertEquals("10 is too young to be here!", httpClient.getText()); }); } } }</pre> <p> If the routed-to action throws an exception, it will be forwarded down the promise chain.
 * @param predicate the condition under which the value should be routed
 * @param action the terminal action for the value
 * @return a routed promise
 */
default Promise<T> route(Predicate<? super T> predicate,Action<? super T> action){
  return transform(up -> down -> up.connect(down.<T>onSuccess(value -> {
    boolean apply;
    try {
      apply=predicate.apply(value);
    }
 catch (    Throwable e) {
      down.error(e);
      return;
    }
    if (apply) {
      try {
        action.execute(value);
        down.complete();
      }
 catch (      Throwable e) {
        down.error(e);
      }
    }
 else {
      down.success(value);
    }
  }
)));
}
