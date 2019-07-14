/** 
 * Converts Jodd logging level to JDK.
 */
private org.apache.logging.log4j.Level jodd2log4j2(final Logger.Level level){
switch (level) {
case TRACE:
    return org.apache.logging.log4j.Level.TRACE;
case DEBUG:
  return org.apache.logging.log4j.Level.DEBUG;
case INFO:
return org.apache.logging.log4j.Level.INFO;
case WARN:
return org.apache.logging.log4j.Level.WARN;
case ERROR:
return org.apache.logging.log4j.Level.ERROR;
default :
throw new IllegalArgumentException();
}
}
