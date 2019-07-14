/** 
 * <p> Get the names of all of the <code> {@link org.quartz.Job}</code> groups. </p>
 */
@Override public List<String> getJobGroupNames() throws JobPersistenceException {
  lock();
  try {
    return new ArrayList<String>(jobFacade.getAllGroupNames());
  }
  finally {
    unlock();
  }
}
