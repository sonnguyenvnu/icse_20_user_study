public <V>void map(SourceGetter<S> sourceGetter,DestinationSetter<D,V> destinationSetter){
  PropertyReferenceCollector collector=new PropertyReferenceCollector(typeMap.configuration,options);
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
  try {
    D destination=ProxyFactory.proxyFor(typeMap.getDestinationType(),collector.newDestinationInterceptor(),collector.getProxyErrors());
    destinationSetter.accept(destination,destinationValue(destinationSetter));
  }
 catch (  NullPointerException e) {
    if (collector.getProxyErrors().hasErrors())     throw collector.getProxyErrors().toException();
    throw e;
  }
catch (  ErrorsException e) {
    throw e.getErrors().toConfigurationException();
  }
  typeMap.addMapping(collector.collect());
}
