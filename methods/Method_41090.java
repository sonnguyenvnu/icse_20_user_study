public void triggersPaused(String triggerGroup){
  Map<String,String> map=new HashMap<String,String>();
  map.put("triggerName",null);
  map.put("triggerGroup",triggerGroup);
  sendNotification(TRIGGERS_PAUSED,map);
}
