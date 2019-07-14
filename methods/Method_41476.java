public boolean interrupt(JobKey jobKey) throws UnableToInterruptJobException {
  return sched.interrupt(jobKey);
}
