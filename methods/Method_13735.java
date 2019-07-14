@Override public ConsumerDestination provisionConsumerDestination(String name,String group,ExtendedConsumerProperties<RocketMQConsumerProperties> properties) throws ProvisioningException {
  checkTopic(name);
  return new RocketConsumerDestination(name);
}
