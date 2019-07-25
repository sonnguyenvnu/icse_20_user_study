public static Dependency create(String groupId,String artifactId,String version,String scope){
  Dependency dependency=withId(null,groupId,artifactId,version);
  dependency.setScope(scope);
  return dependency;
}
