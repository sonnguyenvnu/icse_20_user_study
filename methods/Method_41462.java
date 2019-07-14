protected SchedulerException invalidateHandleCreateException(String msg,Exception cause){
  rsched=null;
  SchedulerException ex=new SchedulerException(msg,cause);
  return ex;
}
