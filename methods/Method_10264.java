public static void blockRetryExceptionClass(Class<?> cls){
  if (cls != null) {
    RetryHandler.addClassToBlacklist(cls);
  }
}
