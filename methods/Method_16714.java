@Override public ActivityImpl getStartEvent(String procDefId){
  return findActivity(procDefId,activity -> "startEvent".equals(activity.getProperty("type")));
}
