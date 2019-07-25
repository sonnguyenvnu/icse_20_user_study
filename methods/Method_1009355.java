public <V>void skip(DestinationSetter<D,V> destinationSetter){
  options.skipType=1;
  PropertyReferenceCollector collector=new PropertyReferenceCollector(typeMap.configuration,options);
  D destination=ProxyFactory.proxyFor(typeMap.getDestinationType(),collector.newDestinationInterceptor(),collector.getErrors());
  destinationSetter.accept(destination,destinationValue(destinationSetter));
  typeMap.addMapping(collector.collect());
}
