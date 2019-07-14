public void updateWithNewCalendar(Calendar cal,long misfireThreshold,TriggerFacade triggerFacade){
  this.trigger.updateWithNewCalendar(cal,misfireThreshold);
  rePut(triggerFacade);
}
