/** 
 * This is a placeholder for the expression T.class. The type argument must always be specified explicitly, e.g.  {@code Refaster.<String>clazz()}. <p>For example, instead of writing the broken <pre><code> {@literal @}AfterTemplate &lt;T&gt; T[] getEnumConstants() { return T.class.getEnumConstants(); // you want to inline this, but it won't compile } </code></pre> you would instead write <pre><code> {@literal @}AfterTemplate &lt;T&gt; T[] getEnumConstants() { return Refaster.&lt;T&gt;clazz().getEnumConstants(); } </code></pre>
 * @throws IllegalArgumentException if T is not specified explicitly.
 */
public static <T>Class<T> clazz(){
  throw new UnsupportedOperationException();
}
