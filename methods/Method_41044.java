protected void notifyJobStoreJobVetoed(OperableTrigger trigger,JobDetail detail,CompletedExecutionInstruction instCode){
  resources.getJobStore().triggeredJobComplete(trigger,detail,instCode);
}
