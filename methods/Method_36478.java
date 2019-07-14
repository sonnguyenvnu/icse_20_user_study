public Object[] loadContributions(ExtensionInternal extension) throws Exception {
  if (contributions != null) {
    if (xmap == null) {
      xmap=new XMap();
      for (      Class<?> contrib : contributions) {
        xmap.register(contrib);
      }
    }
    Object[] contribs=xmap.loadAll(new XMapContext(extension.getAppClassLoader()),extension.getElement());
    extension.setContributions(contribs);
    return contribs;
  }
  return null;
}
