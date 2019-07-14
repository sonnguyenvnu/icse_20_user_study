/** 
 * Searches for the particular class in the default list of imports as well as the Sketch classpath
 * @param className
 * @return
 */
protected static Class<?> findClassIfExists(PreprocessedSketch ps,String className){
  if (className == null) {
    return null;
  }
  if (className.indexOf('.') >= 0) {
    String[] parts=className.split("\\.");
    String newClassName=parts[0];
    int i=1;
    while (i < parts.length && ps.classPath.isPackage(newClassName)) {
      newClassName=newClassName + "/" + parts[i++];
    }
    while (i < parts.length) {
      newClassName=newClassName + "$" + parts[i++];
    }
    className=newClassName.replace('/','.');
  }
  Class<?> tehClass=loadClass(className,ps.classLoader);
  if (tehClass != null) {
    return tehClass;
  }
  if (className.indexOf('.') >= 0) {
    return null;
  }
  log("Looking in the classloader for " + className);
  List<ImportStatement> programImports=ps.programImports;
  List<ImportStatement> codeFolderImports=ps.codeFolderImports;
  List<ImportStatement> coreAndDefaultImports=ps.coreAndDefaultImports;
  ImportStatement javaLang=ImportStatement.wholePackage("java.lang");
  Stream<List<ImportStatement>> importListStream=Stream.of(Collections.singletonList(javaLang),coreAndDefaultImports,programImports,codeFolderImports);
  final String finalClassName=className;
  return importListStream.map(list -> list.stream().map(is -> {
    if (is.getClassName().equals(finalClassName)) {
      return is.getFullClassName();
    }
 else     if (is.isStarredImport()) {
      return is.getPackageName() + "." + finalClassName;
    }
    return null;
  }
).filter(name -> name != null).map(name -> loadClass(name,ps.classLoader)).filter(cls -> cls != null).findAny()).filter(Optional::isPresent).map(Optional::get).findAny().orElse(null);
}
