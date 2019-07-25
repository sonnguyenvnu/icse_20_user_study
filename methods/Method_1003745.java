/** 
 * Applies the custom operation function to this promise. <p> This method can be used to apply custom operations without breaking the “code flow”. It works particularly well with method references. <pre class="java"> {@code import ratpack.exec.Promise; import ratpack.test.exec.ExecHarness; import static org.junit.Assert.assertEquals;}public class Example  public static void main(String... args) throws Exception { Integer value = ExecHarness.yieldSingle(e -> Promise.value(1) .apply(Example::dubble) .apply(Example::triple) ).getValue(); assertEquals(Integer.valueOf(6), value); } public static Promise<Integer> dubble(Promise<Integer> input) { return input.map(i -> i * 2); } public static Promise<Integer> triple(Promise<Integer> input) { return input.map(i -> i * 3); } } }</pre> <p> If the apply function throws an exception, the returned promise will fail. <pre class="java"> {@code import ratpack.exec.Promise; import ratpack.test.exec.ExecHarness; import static org.junit.Assert.assertEquals;}public class Example  public static void main(String... args) throws Exception { Throwable error = ExecHarness.yieldSingle(e -> Promise.value(1) .apply(Example::explode) ).getThrowable(); assertEquals("bang!", error.getMessage()); } public static Promise<Integer> explode(Promise<Integer> input) throws Exception { throw new Exception("bang!"); } } }</pre> <p> If the promise having the operation applied to fails, the operation will not be applied. <pre class="java"> {@code import ratpack.exec.Promise; import ratpack.test.exec.ExecHarness; import static org.junit.Assert.assertEquals;}public class Example  public static void main(String... args) throws Exception { Throwable error = ExecHarness.yieldSingle(e -> Promise.<Integer>error(new Exception("bang!")) .apply(Example::dubble) ).getThrowable(); assertEquals("bang!", error.getMessage()); } public static Promise<Integer> dubble(Promise<Integer> input) { return input.map(i -> i * 2); } } }</pre>
 * @param < O > the type of promised object after the operation
 * @param function the operation implementation
 * @return the transformed promise
 */
default <O>Promise<O> apply(Function<? super Promise<T>,? extends Promise<O>> function){
  try {
    return function.apply(this);
  }
 catch (  Throwable e) {
    return Promise.error(e);
  }
}
