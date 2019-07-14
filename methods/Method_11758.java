private static String formatClass(Class<?> value){
  String className=value.getCanonicalName();
  return className == null ? value.getName() : className;
}
