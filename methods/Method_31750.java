public Log createLogger(Class<?> clazz){
  return new MavenLog(mojo.getLog());
}
