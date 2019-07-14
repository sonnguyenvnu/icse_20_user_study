/** 
 * Returns a new fixture for running a test. Default implementation executes the test class's no-argument constructor (validation should have ensured one exists).
 */
protected Object createTest() throws Exception {
  return getTestClass().getOnlyConstructor().newInstance();
}
