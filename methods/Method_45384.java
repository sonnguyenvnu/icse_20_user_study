private Resource resourceFrom(final BaseResourceSetting resourceSetting){
  for (  String resource : RESOURCES) {
    try {
      Field field=getField(resourceSetting.getClass(),resource);
      field.setAccessible(true);
      return resourceFrom(resource,(TextContainer)field.get(resourceSetting));
    }
 catch (    Exception ignored) {
    }
  }
  throw new IllegalArgumentException("resourceSetting is expected");
}
