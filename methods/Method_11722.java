private static void configLogger(String value){
  Logger rootLogger=Logger.getRootLogger();
  if ("debug".equalsIgnoreCase(value)) {
    rootLogger.setLevel(Level.DEBUG);
  }
 else   if ("info".equalsIgnoreCase(value)) {
    rootLogger.setLevel(Level.INFO);
  }
 else   if ("warn".equalsIgnoreCase(value)) {
    rootLogger.setLevel(Level.WARN);
  }
 else   if ("trace".equalsIgnoreCase(value)) {
    rootLogger.setLevel(Level.TRACE);
  }
 else   if ("off".equalsIgnoreCase(value)) {
    rootLogger.setLevel(Level.OFF);
  }
 else   if ("error".equalsIgnoreCase(value)) {
    rootLogger.setLevel(Level.ERROR);
  }
}
