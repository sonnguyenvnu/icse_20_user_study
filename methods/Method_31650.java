@Override public Collection<Class<?>> scanForClasses(){
  LOG.debug("Scanning for classes at " + location);
  List<Class<?>> classes=new ArrayList<>();
  for (  LoadableResource resource : resources) {
    if (resource.getAbsolutePath().endsWith(".class")) {
      Class<?> clazz=ClassUtils.loadClass(toClassName(resource.getAbsolutePath()),classLoader);
      if (clazz != null) {
        classes.add(clazz);
      }
    }
  }
  return classes;
}
