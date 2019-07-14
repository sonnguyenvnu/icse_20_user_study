@Override public void setResourceLoader(ResourceLoader resourceLoader){
  if (DefaultResourceLoader.class.isAssignableFrom(resourceLoader.getClass())) {
    ((DefaultResourceLoader)resourceLoader).addProtocolResolver(this);
  }
 else {
    log.warn("The provided delegate resource loader is not an implementation " + "of DefaultResourceLoader. Custom Protocol using oss:// prefix will not be enabled.");
  }
}
