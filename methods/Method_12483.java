@Override public InstanceId generateId(Registration registration){
  String applicationId=registration.getMetadata().get("applicationId");
  String instanceId=registration.getMetadata().get("instanceId");
  if (StringUtils.hasText(applicationId) && StringUtils.hasText(instanceId)) {
    return InstanceId.of(String.format("%s:%s",applicationId,instanceId));
  }
  return fallbackIdGenerator.generateId(registration);
}
