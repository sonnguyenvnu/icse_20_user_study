public void apply(final Builder builder){
  final PoolingOptions pooling=new PoolingOptions();
  this.maxRequestsPerConnection.ifPresent(x -> {
    for (    Map.Entry<HostDistance,Integer> entry : x.entrySet()) {
      pooling.setMaxRequestsPerConnection(entry.getKey(),entry.getValue());
    }
  }
);
  this.coreConnectionsPerHost.ifPresent(x -> {
    for (    Map.Entry<HostDistance,Integer> entry : x.entrySet()) {
      pooling.setCoreConnectionsPerHost(entry.getKey(),entry.getValue());
    }
  }
);
  this.maxConnectionsPerHost.ifPresent(x -> {
    for (    Map.Entry<HostDistance,Integer> entry : x.entrySet()) {
      pooling.setMaxConnectionsPerHost(entry.getKey(),entry.getValue());
    }
  }
);
  this.newConnectionThreshold.ifPresent(x -> {
    for (    Map.Entry<HostDistance,Integer> entry : x.entrySet()) {
      pooling.setNewConnectionThreshold(entry.getKey(),entry.getValue());
    }
  }
);
  this.maxQueueSize.ifPresent(pooling::setMaxQueueSize);
  this.poolTimeoutMillis.ifPresent(pooling::setPoolTimeoutMillis);
  this.idleTimeoutSeconds.ifPresent(pooling::setIdleTimeoutSeconds);
  this.heartbeatIntervalSeconds.ifPresent(pooling::setHeartbeatIntervalSeconds);
  builder.withPoolingOptions(pooling);
}
