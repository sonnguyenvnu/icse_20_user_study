/** 
 * {@inheritDoc}
 */
@Override public SimpleLogger createLogger(final String name){
  return loggers.computeIfAbsent(name,simpleLoggerFunction);
}
