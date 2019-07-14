public void jobScheduled(Trigger trigger){
  sendNotification(JOB_SCHEDULED,TriggerSupport.toCompositeData(trigger));
}
