private static Platform determinePlatformReflectively(){
  if (System.getProperty("com.google.appengine.runtime.environment") == null) {
    return Platform.STANDARD;
  }
  if (System.getenv("GAE_LONG_APP_ID") != null) {
    return Platform.APPENGINE_FLEXIBLE;
  }
  try {
    boolean isInsideAppengine=Class.forName("com.google.apphosting.api.ApiProxy").getMethod("getCurrentEnvironment").invoke(null) != null;
    return isInsideAppengine ? Platform.APPENGINE_STANDARD : Platform.STANDARD;
  }
 catch (  ClassNotFoundException e) {
    return Platform.STANDARD;
  }
catch (  InvocationTargetException e) {
    return Platform.STANDARD;
  }
catch (  IllegalAccessException e) {
    return Platform.STANDARD;
  }
catch (  NoSuchMethodException e) {
    return Platform.STANDARD;
  }
}
