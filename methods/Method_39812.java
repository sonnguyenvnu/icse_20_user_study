public T withAspects(final A... aspects){
  Collections.addAll(proxyAspectList,aspects);
  return _this();
}
