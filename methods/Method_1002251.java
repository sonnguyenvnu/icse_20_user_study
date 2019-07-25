public static void initialize(){
  try {
    instance=new ClasspathPropertiesConfiguration();
    loadResources(propertiesResourceRelativePath);
  }
 catch (  Exception e) {
    throw new RuntimeException("failed to read configuration properties from classpath",e);
  }
}
