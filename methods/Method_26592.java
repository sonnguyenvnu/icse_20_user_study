/** 
 * Indicates that Refaster should treat this  {@code @Repeated} argument specifically as a varargsargument. <p>For example, you might write <pre><code> {@literal @}BeforeTemplate void something(@Repeated T arg) { Stream.of(asVarargs(arg)); // doesn't use the Stream.of(T) overload, but Stream.of(T...) } </code></pre>
 */
public static <T>T[] asVarargs(T arg){
  throw new UnsupportedOperationException();
}
