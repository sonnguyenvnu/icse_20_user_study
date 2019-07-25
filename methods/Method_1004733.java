@Override public void init(Properties properties){
  String loggerName=properties.getProperty(CONF_LOGGER_NAME);
  String loggerClassName=properties.getProperty(CONF_LOGGER_CLASS);
  Class loggerClass;
  if (loggerClassName != null) {
    try {
      loggerClass=Class.forName(loggerClassName);
    }
 catch (    ClassNotFoundException cnfe) {
      throw new EsHadoopIllegalArgumentException("Could not locate logger class [" + loggerClassName + "].",cnfe);
    }
  }
 else {
    loggerClass=null;
  }
  if (loggerName != null && loggerClass != null) {
    throw new EsHadoopIllegalArgumentException("Both logger name and logger class provided for drop and log handler. Provide only one. Bailing out...");
  }
  if (loggerName != null) {
    logger=LogFactory.getLog(loggerName);
  }
 else   if (loggerClass != null) {
    logger=LogFactory.getLog(loggerClass);
  }
 else {
    throw new EsHadoopIllegalArgumentException("No logger name or logger class provided for drop and log handler. Provide one. Bailing out...");
  }
  String rawLoggerLevel=properties.getProperty(CONF_LOGGER_LEVEL,LogLevel.WARN.name());
  if (!LogLevel.names.contains(rawLoggerLevel)) {
    throw new EsHadoopIllegalArgumentException("Invalid logger level [" + rawLoggerLevel + "] given. Available logging levels: " + LogLevel.names.toString());
  }
  loggerLevel=LogLevel.valueOf(rawLoggerLevel);
}
