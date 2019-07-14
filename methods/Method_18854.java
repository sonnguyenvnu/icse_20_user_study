@Nullable private static SpecMethodModel<DelegateMethod,Void> getCorrespondingOnCalculateCachedValueMethod(CachedValueParamModel cachedValue,List<SpecMethodModel<DelegateMethod,Void>> onCalculateCachedValueMethods){
  for (  SpecMethodModel<DelegateMethod,Void> onCalculateCachedValueMethod : onCalculateCachedValueMethods) {
    final String cachedValueName=getCachedValueName(onCalculateCachedValueMethod);
    if (cachedValueName != null && cachedValueName.equals(cachedValue.getName())) {
      return onCalculateCachedValueMethod;
    }
  }
  return null;
}
