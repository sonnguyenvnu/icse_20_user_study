private static URL classModuleUrl(ClassLoader classLoader,Class clazz){
  if (clazz == null) {
    return null;
  }
  final String name=clazz.getName().replace('.','/') + ".class";
  return resourceModuleUrl(classLoader,name);
}
