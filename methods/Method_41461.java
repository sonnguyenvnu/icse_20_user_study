/** 
 * @see org.quartz.Scheduler#getPausedTriggerGroups()
 */
@SuppressWarnings("unchecked") public Set<String> getPausedTriggerGroups() throws SchedulerException {
  return (Set<String>)getAttribute("PausedTriggerGroups");
}
