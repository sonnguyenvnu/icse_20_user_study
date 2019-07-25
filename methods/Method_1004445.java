private boolean exist(final List<BrokerMeta> brokers,final BrokerMeta broker){
  for (  BrokerMeta brokerMeta : brokers) {
    if (broker.getRole() == brokerMeta.getRole())     return true;
  }
  return false;
}
