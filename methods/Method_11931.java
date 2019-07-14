/** 
 * Verify that your code throws an exception whose message contains a specific text. <pre> &#064;Test public void throwsExceptionWhoseMessageContainsSpecificText() { thrown.expectMessage(&quot;happened&quot;); throw new NullPointerException(&quot;What happened?&quot;); }</pre>
 */
public void expectMessage(String substring){
  expectMessage(containsString(substring));
}
