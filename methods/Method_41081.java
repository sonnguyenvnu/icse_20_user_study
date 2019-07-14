public void jobUnscheduled(TriggerKey triggerKey){
  Map<String,String> map=new HashMap<String,String>();
  map.put("triggerName",triggerKey.getName());
  map.put("triggerGroup",triggerKey.getGroup());
  sendNotification(JOB_UNSCHEDULED,map);
}
