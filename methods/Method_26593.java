/** 
 * Indicates that Refaster should attempt to match a target expression against each of the specified template expressions, in order, and succeed at the first match. <p>This method should only be used in Refaster templates, but should never actually be run. <p>For example, instead of writing <pre><code> {@literal @}BeforeTemplate &lt;E&gt; List&lt;E&gt; copyOfSingleton(E element) { return ImmutableList.copyOf(Collections.singletonList(element)); } {@literal @}BeforeTemplate &lt;E&gt; List&lt;E&gt; copyOfArrayList(E element) { return ImmutableList.copyOf(Lists.newArrayList(element)); } </code></pre> <p>one could alternately write <pre><code> {@literal @}BeforeTemplate &lt;E&gt; List&lt;E&gt; singleton(E element) { return ImmutableList.copyOf(Refaster.anyOf( Collections.singletonList(element), Lists.newArrayList(element))); } </code></pre>
 */
@SafeVarargs public static <T>T anyOf(T... expressions){
  throw new UnsupportedOperationException();
}
