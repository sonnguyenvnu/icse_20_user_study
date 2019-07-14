protected Message getStatusChangedMessage(Instance instance,String statusFrom,String statusTo){
  String activitySubtitle=this.safeFormat(statusActivitySubtitlePattern,instance.getRegistration().getName(),instance.getId(),statusFrom,statusTo);
  return createMessage(instance,statusChangedTitle,activitySubtitle);
}
