/** 
 * Defines class.
 */
public Class define(){
  process();
  if ((!proxetta.isForced()) && (!isProxyApplied())) {
    if (log.isDebugEnabled()) {
      log.debug("Proxy not applied: " + StringUtil.toSafeString(targetClassName));
    }
    if (targetClass != null) {
      return targetClass;
    }
    if (targetClassName != null) {
      try {
        return ClassLoaderUtil.loadClass(targetClassName);
      }
 catch (      ClassNotFoundException cnfex) {
        throw new ProxettaException(cnfex);
      }
    }
  }
  if (log.isDebugEnabled()) {
    log.debug("Proxy created: " + StringUtil.toSafeString(targetClassName));
  }
  try {
    ClassLoader classLoader=proxetta.getClassLoader();
    if (classLoader == null) {
      classLoader=ClassLoaderUtil.getDefaultClassLoader();
      if ((classLoader == null) && (targetClass != null)) {
        classLoader=targetClass.getClassLoader();
      }
    }
    final byte[] bytes=toByteArray();
    dumpClassInDebugFolder(bytes);
    return DefineClass.of(getProxyClassName(),bytes,classLoader);
  }
 catch (  Exception ex) {
    throw new ProxettaException("Class definition failed",ex);
  }
}
