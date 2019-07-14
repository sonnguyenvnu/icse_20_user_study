@Override protected Object buildProxy(ClientTransportConfig transportConfig) throws SofaRpcException {
  SofaResteasyClientBuilder builder=new SofaResteasyClientBuilder();
  ResteasyClient client=builder.registerProvider().logProviders().establishConnectionTimeout(transportConfig.getConnectTimeout(),TimeUnit.MILLISECONDS).socketTimeout(transportConfig.getInvokeTimeout(),TimeUnit.MILLISECONDS).connectionPoolSize(Math.max(transportConfig.getConnectionNum(),MIN_CONNECTION_POOL_SIZE)).build();
  ProviderInfo provider=transportConfig.getProviderInfo();
  String url="http://" + provider.getHost() + ":" + provider.getPort() + StringUtils.CONTEXT_SEP + StringUtils.trimToEmpty(provider.getPath());
  ResteasyWebTarget target=client.target(url);
  return target.proxy(ClassUtils.forName(transportConfig.getConsumerConfig().getInterfaceId()));
}
