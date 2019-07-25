private void connect(){
  ManagedChannelBuilder<?> channelBuilder;
  if (useInsecure) {
    channelBuilder=ManagedChannelBuilder.forTarget(endPoint).usePlaintext();
  }
 else {
    channelBuilder=NettyChannelBuilder.forTarget(endPoint).negotiationType(NegotiationType.TLS).sslContext(sslContext);
  }
  ManagedChannel channel=channelBuilder.build();
  MetricsServiceGrpc.MetricsServiceStub stub=MetricsServiceGrpc.newStub(channel);
  exportRpcHandler=OcAgentMetricsServiceExportRpcHandler.create(stub);
  ExportMetricsServiceRequest.Builder builder=ExportMetricsServiceRequest.newBuilder().setNode(OcAgentNodeUtils.getNodeInfo(serviceName));
  @Nullable Resource resourceProto=OcAgentNodeUtils.getAutoDetectedResourceProto();
  if (resourceProto != null) {
    builder.setResource(resourceProto);
  }
  exportRpcHandler.onExport(builder.build());
}
