/** 
 * This is a special method to emit a one-line comment. The comment argument must always be a string literal. This method cannot be static imported. <p>For example, instead of writing <pre><code> {@literal @}AfterTemplate void printWithComment(String str) { // comment System.out.println(str); } </code></pre> <p>you would instead write <pre><code> {@literal @}AfterTemplate void printWithComment(String str) { Refaster.emitComment("comment"); System.out.println(str); } </code></pre>
 */
public static void emitComment(String literal){
  throw new UnsupportedOperationException();
}
