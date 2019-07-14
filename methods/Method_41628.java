protected void setAllTriggersOfJobToState(JobKey jobKey,int state){
  ArrayList<TriggerWrapper> tws=getTriggerWrappersForJob(jobKey);
  for (  TriggerWrapper tw : tws) {
    tw.state=state;
    if (state != TriggerWrapper.STATE_WAITING) {
      timeTriggers.remove(tw);
    }
  }
}
