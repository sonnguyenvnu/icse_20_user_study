protected Message getDeregisteredMessage(Instance instance){
  String activitySubtitle=this.safeFormat(deregisterActivitySubtitlePattern,instance.getRegistration().getName(),instance.getId());
  return createMessage(instance,deRegisteredTitle,activitySubtitle);
}
