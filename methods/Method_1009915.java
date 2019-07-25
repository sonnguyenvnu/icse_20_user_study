/** 
 * Formats the log message and any metadata for the given  {@link LogData}, calling the supplied receiver object with the results.
 */
static void format(LogData logData,SimpleLogHandler receiver){
  SimpleMessageFormatter.format(logData,receiver);
}
