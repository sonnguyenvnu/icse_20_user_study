/** 
 * Create a <code>Description</code> named after <code>testClass</code>
 * @param testClass A {@link Class} containing tests
 * @return a <code>Description</code> of <code>testClass</code>
 */
public static Description createSuiteDescription(Class<?> testClass){
  return new Description(testClass,testClass.getName(),testClass.getAnnotations());
}
