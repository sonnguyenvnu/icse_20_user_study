/** 
 * Check if proxy should be applied on method and return proxy method builder if so. Otherwise, returns <code>null</code>.
 */
protected ProxettaMethodBuilder applyProxy(final MethodSignatureVisitor msign){
  List<ProxyAspectData> aspectList=matchMethodPointcuts(msign);
  if (aspectList == null) {
    return null;
  }
  int access=msign.getAccessFlags();
  if ((access & ACC_ABSTRACT) != 0) {
    throw new ProxettaException("Unable to process abstract method: " + msign);
  }
  wd.proxyApplied=true;
  return new ProxettaMethodBuilder(msign,wd,aspectList);
}
