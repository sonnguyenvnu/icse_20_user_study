public Log createLogger(Class<?> clazz){
  return new Slf4jLog(LoggerFactory.getLogger(clazz));
}
