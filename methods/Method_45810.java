@Override public ProviderInfo doSelect(SofaRequest request,List<ProviderInfo> providerInfos){
  String interfaceId=request.getInterfaceName();
  String method=request.getMethodName();
  String key=interfaceId + "#" + method;
  int hashcode=providerInfos.hashCode();
  Selector selector=selectorCache.get(key);
  if (selector == null || selector.getHashCode() != hashcode) {
    selector=new Selector(interfaceId,method,providerInfos,hashcode);
    selectorCache.put(key,selector);
  }
  return selector.select(request);
}
