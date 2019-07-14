private Level determineGradleLogLevel(BuildListener l){
  try {
    project.log("Detected gradle AntLoggingAdapter",Project.MSG_DEBUG);
    Field loggerField=l.getClass().getDeclaredField("logger");
    loggerField.setAccessible(true);
    Object logger=loggerField.get(l);
    Class<?> gradleLogLevel=l.getClass().getClassLoader().loadClass("org.gradle.api.logging.LogLevel");
    Method isLevelAtMostMethod=logger.getClass().getDeclaredMethod("isLevelAtMost",gradleLogLevel);
    isLevelAtMostMethod.setAccessible(true);
    Object[] logLevels=gradleLogLevel.getEnumConstants();
    Level[] mapping=new Level[]{Level.FINEST,Level.CONFIG,Level.INFO,Level.WARNING,Level.SEVERE,Level.SEVERE};
    for (int i=0; i < Math.min(logLevels.length,mapping.length); i++) {
      boolean enabled=(boolean)isLevelAtMostMethod.invoke(logger,logLevels[i]);
      if (enabled) {
        project.log("Current log level: " + logLevels[i] + " -> " + mapping[i],Project.MSG_DEBUG);
        return mapping[i];
      }
    }
  }
 catch (  ReflectiveOperationException ignored) {
  }
  project.log("Could not determine log level, falling back to default: " + DEFAULT_LEVEL,Project.MSG_WARN);
  return DEFAULT_LEVEL;
}
