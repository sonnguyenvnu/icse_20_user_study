public static String external(ExternalDependency dep){
  return String.format("//%s:%s",dep.getTargetPath(),dep.getTargetName());
}
