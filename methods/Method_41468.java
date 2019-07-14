public synchronized void bind(Scheduler sched) throws SchedulerException {
  if ((Scheduler)schedulers.get(sched.getSchedulerName()) != null) {
    throw new SchedulerException("Scheduler with name '" + sched.getSchedulerName() + "' already exists.");
  }
  schedulers.put(sched.getSchedulerName(),sched);
}
