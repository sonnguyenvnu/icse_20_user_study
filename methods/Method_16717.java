@Override public ActivityImpl getEndEvent(String procDefId){
  return findActivity(procDefId,activity -> "endEvent".equals(activity.getProperty("type")));
}
