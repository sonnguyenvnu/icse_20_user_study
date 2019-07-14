@Override public void forceCloseInstance(String instanceId){
  Preconditions.checkArgument(!graph.getConfiguration().getUniqueGraphId().equals(instanceId),"Cannot force close this current instance [%s]. Properly shut down the graph instead.",instanceId);
  Preconditions.checkArgument(modifyConfig.has(REGISTRATION_TIME,instanceId),"Instance [%s] is not currently open",instanceId);
  Instant registrationTime=modifyConfig.get(REGISTRATION_TIME,instanceId);
  Preconditions.checkArgument(registrationTime.compareTo(txStartTime) < 0,"The to-be-closed instance [%s] was started after this transaction" + "which indicates a successful restart and can hence not be closed: %s vs %s",instanceId,registrationTime,txStartTime);
  modifyConfig.remove(REGISTRATION_TIME,instanceId);
}
