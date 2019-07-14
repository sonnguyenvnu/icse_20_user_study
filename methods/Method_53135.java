private static LoggerFactory getLoggerFactoryIfAvailable(String checkClassName,String implementationClass){
  try {
    Class.forName(checkClassName);
    return (LoggerFactory)Class.forName(implementationClass).newInstance();
  }
 catch (  ClassNotFoundException ignore) {
  }
catch (  InstantiationException e) {
    throw new AssertionError(e);
  }
catch (  SecurityException ignore) {
  }
catch (  IllegalAccessException e) {
    throw new AssertionError(e);
  }
  return null;
}
