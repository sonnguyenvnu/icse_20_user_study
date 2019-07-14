/** 
 * Provider???? ClientTransportConfig
 * @param providerInfo Provider
 * @return ClientTransportConfig
 */
protected ClientTransportConfig providerToClientConfig(ProviderInfo providerInfo){
  return new ClientTransportConfig().setConsumerConfig(consumerConfig).setProviderInfo(providerInfo).setContainer(consumerConfig.getProtocol()).setConnectTimeout(consumerConfig.getConnectTimeout()).setInvokeTimeout(consumerConfig.getTimeout()).setDisconnectTimeout(consumerConfig.getDisconnectTimeout()).setConnectionNum(consumerConfig.getConnectionNum()).setChannelListeners(consumerConfig.getOnConnect());
}
