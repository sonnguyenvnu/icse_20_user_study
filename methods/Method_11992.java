/** 
 * Create a <code>Request</code> that, when processed, will run all the tests in a set of classes.
 * @param computer Helps construct Runners from classes
 * @param classes the classes containing the tests
 * @return a <code>Request</code> that will cause all tests in the classes to be run
 */
public static Request classes(Computer computer,Class<?>... classes){
  try {
    AllDefaultPossibilitiesBuilder builder=new AllDefaultPossibilitiesBuilder();
    Runner suite=computer.getSuite(builder,classes);
    return runner(suite);
  }
 catch (  InitializationError e) {
    return runner(new ErrorReportingRunner(e,classes));
  }
}
