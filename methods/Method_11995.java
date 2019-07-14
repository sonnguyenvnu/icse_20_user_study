/** 
 * Returns a Request whose Tests can be run in a certain order, defined by <code>comparator</code> <p> For example, here is code to run a test suite in alphabetical order: <pre> private static Comparator&lt;Description&gt; forward() { return new Comparator&lt;Description&gt;() { public int compare(Description o1, Description o2) { return o1.getDisplayName().compareTo(o2.getDisplayName()); } }; } public static main() { new JUnitCore().run(Request.aClass(AllTests.class).sortWith(forward())); } </pre>
 * @param comparator definition of the order of the tests in this Request
 * @return a Request with ordered Tests
 */
public Request sortWith(Comparator<Description> comparator){
  return new SortingRequest(this,comparator);
}
