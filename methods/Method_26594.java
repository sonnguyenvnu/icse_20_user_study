/** 
 * This is a placeholder for  {@code new T[size]}. The type argument must always be specified explicitly, e.g.  {@code Refaster.<String>newArray(10)}. <p>For example, instead of writing the broken <pre><code> {@literal @}AfterTemplate &lt;T&gt; T[] newTArray(int size) { return new T[size]; // you want to generate this code, but it won't compile } </code></pre> <p>you would instead write <pre><code> {@literal @}AfterTemplate &lt;T&gt; T[] newTArray(int size) { return Refaster.&lt;T&gt;newArray(size); } </code></pre>
 * @throws IllegalArgumentException if T is not specified explicitly.
 */
public static <T>T[] newArray(int size){
  throw new UnsupportedOperationException(Integer.toString(size));
}
