private static String getCachedValueGetterName(CharSequence cachedValueName){
  return "get" + toUpperCaseFirstLetter(cachedValueName);
}
