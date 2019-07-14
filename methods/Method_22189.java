/** 
 * @return true is ACRA has been initialised.
 */
public static boolean isInitialised(){
  return errorReporterSingleton instanceof ErrorReporterImpl;
}
