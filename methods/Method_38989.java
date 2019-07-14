/** 
 * Converts Jodd logging level to JDK.
 */
private java.util.logging.Level jodd2jdk(final Level level){
switch (level) {
case TRACE:
    return java.util.logging.Level.FINER;
case DEBUG:
  return java.util.logging.Level.FINE;
case INFO:
return java.util.logging.Level.INFO;
case WARN:
return java.util.logging.Level.WARNING;
case ERROR:
return java.util.logging.Level.SEVERE;
default :
throw new IllegalArgumentException();
}
}
