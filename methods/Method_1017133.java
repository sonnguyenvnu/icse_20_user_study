@Override public Slf4jQueryLogger create(String component){
  return new Slf4jQueryLogger(logger,objectMapper,component);
}
