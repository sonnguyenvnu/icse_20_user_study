public ReturnNextNonceApi getNextNonceApi(){
  if (null == nextNonceApi) {
    nextNonceApi=RestProxyFactory.createProxy(ReturnNextNonceApi.class,exchangeSpecification.getSslUri());
  }
  return nextNonceApi;
}
