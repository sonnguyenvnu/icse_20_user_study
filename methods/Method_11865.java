private static boolean isMatchesSafelyMethod(Method method){
  return "matchesSafely".equals(method.getName()) && method.getParameterTypes().length == 1 && !method.isSynthetic();
}
