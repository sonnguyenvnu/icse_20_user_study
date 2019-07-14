@Override public Object[] loadContributions(ExtensionInternal extension) throws Exception {
  ApplicationContext applicationContext=null;
  if (extension instanceof SpringExtensionImpl) {
    applicationContext=((SpringExtensionImpl)extension).getApplicationContext();
  }
  if (contributions != null) {
    xmapSpring=new XMapSpring();
    for (    Class<?> contrib : contributions) {
      xmapSpring.register(contrib,applicationContext);
    }
    Object[] contribs=xmapSpring.loadAll(new XMapContext(extension.getAppClassLoader()),extension.getElement());
    for (    Object o : contribs) {
      if (applicationContext != null && o instanceof BeanFactoryAware) {
        ((BeanFactoryAware)o).setBeanFactory(applicationContext.getAutowireCapableBeanFactory());
      }
    }
    extension.setContributions(contribs);
    return contribs;
  }
  return null;
}
