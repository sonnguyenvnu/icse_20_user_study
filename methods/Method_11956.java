/** 
 * Create a suite for  {@code classes}, building Runners with  {@code builder}. Throws an InitializationError if Runner construction fails
 */
public Runner getSuite(final RunnerBuilder builder,Class<?>[] classes) throws InitializationError {
  return new Suite(new RunnerBuilder(){
    @Override public Runner runnerForClass(    Class<?> testClass) throws Throwable {
      return getRunner(builder,testClass);
    }
  }
,classes){
    @Override protected String getName(){
      return "classes";
    }
  }
;
}
