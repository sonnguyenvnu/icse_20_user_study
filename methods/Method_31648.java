@Override public Collection<Class<?>> scanForClasses(){
  String pkg=location.getPath().replace("/",".");
  List<Class<?>> classes=new ArrayList<>();
  String sourceDir=context.getApplicationInfo().sourceDir;
  DexFile dex=null;
  try {
    dex=new DexFile(sourceDir);
    Enumeration<String> entries=dex.entries();
    while (entries.hasMoreElements()) {
      String className=entries.nextElement();
      if (className.startsWith(pkg)) {
        Class<?> clazz=ClassUtils.loadClass(className,clazzLoader);
        if (clazz != null) {
          classes.add(clazz);
        }
      }
    }
  }
 catch (  IOException e) {
    LOG.warn("Unable to scan DEX file (" + sourceDir + "): " + e.getMessage());
  }
 finally {
    if (dex != null) {
      try {
        dex.close();
      }
 catch (      IOException e) {
        LOG.debug("Unable to close DEX file (" + sourceDir + "): " + e.getMessage());
      }
    }
  }
  return classes;
}
