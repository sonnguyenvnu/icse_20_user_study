protected Message getRegisteredMessage(Instance instance){
  String activitySubtitle=this.safeFormat(registerActivitySubtitlePattern,instance.getRegistration().getName(),instance.getId());
  return createMessage(instance,registeredTitle,activitySubtitle);
}
