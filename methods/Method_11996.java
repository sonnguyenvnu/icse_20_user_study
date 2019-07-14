/** 
 * Returns a Request whose Tests can be run in a certain order, defined by <code>ordering</code> <p> For example, here is code to run a test suite in reverse order: <pre> private static Ordering reverse() { return new Ordering() { public List&lt;Description&gt; orderItems(Collection&lt;Description&gt; descriptions) { List&lt;Description&gt; ordered = new ArrayList&lt;&gt;(descriptions); Collections.reverse(ordered); return ordered; } } } public static main() { new JUnitCore().run(Request.aClass(AllTests.class).orderWith(reverse())); } </pre>
 * @return a Request with ordered Tests
 * @since 4.13
 */
public Request orderWith(Ordering ordering){
  return new OrderingRequest(this,ordering);
}
