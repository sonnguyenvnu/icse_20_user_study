public void triggerResumed(TriggerKey triggerKey){
  Map<String,String> map=new HashMap<String,String>();
  if (triggerKey != null) {
    map.put("triggerName",triggerKey.getName());
    map.put("triggerGroup",triggerKey.getGroup());
  }
  sendNotification(TRIGGERS_RESUMED,map);
}
