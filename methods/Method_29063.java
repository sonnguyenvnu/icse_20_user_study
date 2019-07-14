public void setTypeAliasesClassResources(Resource[] resources){
  ArrayList<Class<?>> classList=new ArrayList<Class<?>>();
  for (int i=0; i < resources.length; i++) {
    Resource resource=resources[i];
    try {
      String className;
      if (resource instanceof ClassPathResource) {
        String path=((ClassPathResource)resource).getPath();
        className=getClassNameByPath(path);
      }
 else       if (resource instanceof FileSystemResource) {
        String path=((FileSystemResource)resource).getPath();
        className=getClassNameByPath(path);
      }
 else {
        throw new RuntimeException("resources is unsupported");
      }
      className=packages + className;
      Class<?> clazz=ClassUtils.resolveClassName(className,Thread.currentThread().getContextClassLoader());
      classList.add(clazz);
    }
 catch (    Exception e) {
      LOGGER.error(e.getMessage(),e);
    }
  }
  this.setTypeAliases(classList.toArray(new Class[0]));
}
