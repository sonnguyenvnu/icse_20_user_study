public static LogCreator getLogCreator(ClassLoader classLoader,LogCreator fallbackLogCreator){
  FeatureDetector featureDetector=new FeatureDetector(classLoader);
  if (featureDetector.isAndroidAvailable()) {
    return ClassUtils.instantiate(AndroidLogCreator.class.getName(),classLoader);
  }
  if (featureDetector.isSlf4jAvailable()) {
    return ClassUtils.instantiate(Slf4jLogCreator.class.getName(),classLoader);
  }
  if (featureDetector.isApacheCommonsLoggingAvailable()) {
    return ClassUtils.instantiate(ApacheCommonsLogCreator.class.getName(),classLoader);
  }
  if (fallbackLogCreator == null) {
    return ClassUtils.instantiate(JavaUtilLogCreator.class.getName(),classLoader);
  }
  return fallbackLogCreator;
}
