private static void requiredFunctionMissing(String functionName){
  if (!Configuration.DISABLE_FUNCTION_CHECKS.get(false)) {
    throw new NullPointerException("A required function is missing: " + functionName);
  }
}
