/** 
 * Create a <code>Request</code> that, when processed, will run all the tests in a set of classes with the default <code>Computer</code>.
 * @param classes the classes containing the tests
 * @return a <code>Request</code> that will cause all tests in the classes to be run
 */
public static Request classes(Class<?>... classes){
  return classes(JUnitCore.defaultComputer(),classes);
}
