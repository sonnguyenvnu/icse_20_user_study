@Nullable private static String getCachedValueName(SpecMethodModel<DelegateMethod,Void> onCalculateCachedValueMethod){
  for (  Annotation annotation : onCalculateCachedValueMethod.annotations) {
    if (annotation instanceof OnCalculateCachedValue) {
      return ((OnCalculateCachedValue)annotation).name();
    }
  }
  return null;
}
