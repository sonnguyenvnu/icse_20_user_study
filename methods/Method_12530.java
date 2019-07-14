private static ExchangeFilterFunction toExchangeFilterFunction(InstanceExchangeFilterFunction filter){
  return (request,next) -> request.attribute(ATTRIBUTE_INSTANCE).filter(Instance.class::isInstance).map(Instance.class::cast).map(instance -> filter.filter(instance,request,next)).orElse(next.exchange(request));
}
