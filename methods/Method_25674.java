private static NavigableSet<String> classesStartingWithPackage(NavigableSet<String> unsupportedClasses,String allowedPkg){
  return new TreeSet<>(unsupportedClasses.subSet(allowedPkg,true,allowedPkg + Character.MAX_VALUE,true));
}
