/** 
 * Creates a  {@link Request} that, when processed, will report an error for the giventest class with the given cause.
 */
public static Request errorReport(Class<?> klass,Throwable cause){
  return runner(new ErrorReportingRunner(klass,cause));
}
