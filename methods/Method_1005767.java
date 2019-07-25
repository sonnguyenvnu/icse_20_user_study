private static String targets(Target dep){
  return String.format("//%s:src_%s",dep.getPath(),dep.getName());
}
