public static boolean isAppEngine(){
  return INSTANCE.platform == Platform.APPENGINE_FLEXIBLE || INSTANCE.platform == Platform.APPENGINE_STANDARD;
}
