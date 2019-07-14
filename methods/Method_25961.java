public static boolean doesNotHaveRuntimeRetention(Element element){
  return effectiveRetentionPolicy(element) != RetentionPolicy.RUNTIME;
}
