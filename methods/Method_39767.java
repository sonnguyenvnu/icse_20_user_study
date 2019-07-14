@Override protected ProxettaMethodBuilder applyProxy(final MethodSignatureVisitor msign){
  List<ProxyAspectData> aspectList=matchMethodPointcuts(msign);
  if (aspectList == null) {
    wd.proxyApplied=true;
    createSimpleMethodWrapper(msign);
    return null;
  }
  wd.proxyApplied=true;
  return new ProxettaMethodBuilder(msign,wd,aspectList);
}
