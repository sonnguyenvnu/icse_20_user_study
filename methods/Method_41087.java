public void schedulerError(String msg,SchedulerException cause){
  sendNotification(SCHEDULER_ERROR,cause.getMessage());
}
