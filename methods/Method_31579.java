public Log createLogger(Class<?> clazz){
  return new JavaUtilLog(Logger.getLogger(clazz.getName()));
}
