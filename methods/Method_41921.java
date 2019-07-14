public void setFireInstanceId(String firedInstanceId,TriggerFacade triggerFacade){
  this.trigger.setFireInstanceId(firedInstanceId);
  rePut(triggerFacade);
}
