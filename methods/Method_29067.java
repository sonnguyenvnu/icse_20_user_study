private static String getPackageName(){
  return getAppConfig("project.name") + "." + getAppConfig("project.package");
}
