private static String getAnnotatedName(SpecMethodModel<DelegateMethod,Void> onCalculateCachedValueMethod){
  for (  Annotation annotation : onCalculateCachedValueMethod.annotations) {
    if (annotation instanceof OnCalculateCachedValue) {
      return ((OnCalculateCachedValue)annotation).name();
    }
  }
  throw new RuntimeException("Should be unreachable, please report to Litho team");
}
