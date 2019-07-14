public Log createLogger(Class<?> clazz){
  return new ApacheCommonsLog(LogFactory.getLog(clazz));
}
