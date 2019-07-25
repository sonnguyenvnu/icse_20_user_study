/** 
 * This method runs inspections in separate from main application thread. Running inspections in a separate thread is a workaround for a problem where Spring's DataSourceBuilder doesn't take custom classloader set on Spring's Application Context and instead it takes it from current thread. Without this workaround DataSourceBuilder would end up with Inspector App's classloader which doesn't (and is not supposed to) have classes for running inspections.
 */
public Configuration inspect(Path installPath){
  AppRunner runner=new AppRunner(installPath.getUri(),inspectionsPath,installPath.isDevMode().toString(),projectVersion);
  Future<Configuration> futureResult=execService.submit(runner);
  try {
    return futureResult.get(5,TimeUnit.MINUTES);
  }
 catch (  InterruptedException|ExecutionException e) {
    throw new IllegalStateException("An error occurred",e);
  }
catch (  TimeoutException e) {
    throw new IllegalStateException("Operation timed out",e);
  }
}
