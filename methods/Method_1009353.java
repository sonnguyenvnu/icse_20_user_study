public static <S,D>List<Accessor> collect(TypeMapImpl<S,D> typeMap,TypeSafeSourceGetter<S,?> sourceGetter){
  PropertyReferenceCollector collector=new PropertyReferenceCollector(typeMap.configuration,null);
  try {
    S source=ProxyFactory.proxyFor(typeMap.getSourceType(),collector.newSourceInterceptor(),collector.getProxyErrors());
    Object sourceProperty=sourceGetter.get(source);
    if (source == sourceProperty)     collector.mapFromSource(typeMap.getSourceType());
    if (collector.isNoSourceGetter())     collector.mapFromConstant(sourceProperty);
  }
 catch (  NullPointerException e) {
    if (collector.getProxyErrors().hasErrors())     throw collector.getProxyErrors().toException();
    throw e;
  }
catch (  ErrorsException e) {
    throw e.getErrors().toConfigurationException();
  }
  return collector.accessors;
}
