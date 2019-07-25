/** 
 * Assimilates an existing JUL log manager. Equivalent to calling {@link #assimilate(LoggerRepository)} with <code>LogManager.getLoggerRepository</code>.
 */
public static void assimilate(){
  assimilate(org.apache.log4j.LogManager.getLoggerRepository());
}
