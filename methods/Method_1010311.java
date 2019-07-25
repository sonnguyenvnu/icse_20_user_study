public static List<QualifiedPath> discover(){
  return findJavaRuntimeClasses(new File(System.getProperty("java.home")));
}
