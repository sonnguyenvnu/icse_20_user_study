@Override public EndpointStatus join(InetSocketAddress endpoint,Map<String,InetSocketAddress> additionalEndpoints,int shardId) throws JoinException, InterruptedException {
  return join(endpoint,additionalEndpoints,Optional.of(shardId));
}
