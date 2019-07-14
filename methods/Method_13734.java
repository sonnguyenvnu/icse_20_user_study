@Override public ProducerDestination provisionProducerDestination(String name,ExtendedProducerProperties<RocketMQProducerProperties> properties) throws ProvisioningException {
  checkTopic(name);
  return new RocketProducerDestination(name);
}
