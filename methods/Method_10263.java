public static void allowRetryExceptionClass(Class<?> cls){
  if (cls != null) {
    RetryHandler.addClassToWhitelist(cls);
  }
}
