/** 
 * Loads Madvoc parameters. New  {@link Props} is created from the classpath.
 */
protected Props loadMadvocParams(final String[] patterns){
  if (log.isInfoEnabled()) {
    log.info("Loading Madvoc parameters from: " + Converter.get().toString(patterns));
  }
  try {
    return new Props().loadFromClasspath(patterns);
  }
 catch (  Exception ex) {
    throw new MadvocException("Unable to load Madvoc parameters from: " + Converter.get().toString(patterns) + ".properties': " + ex.toString(),ex);
  }
}
