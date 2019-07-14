public void triggerFinalized(Trigger trigger){
  Map<String,String> map=new HashMap<String,String>();
  map.put("triggerName",trigger.getKey().getName());
  map.put("triggerGroup",trigger.getKey().getGroup());
  sendNotification(TRIGGER_FINALIZED,map);
}
