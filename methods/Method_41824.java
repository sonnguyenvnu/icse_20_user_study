/** 
 * <p> Store the given <code> {@link org.quartz.Job}</code>. </p>
 * @param newJob The <code>Job</code> to be stored.
 * @param replaceExisting If <code>true</code>, any <code>Job</code> existing in the <code>JobStore</code> with thesame name & group should be over-written.
 * @throws ObjectAlreadyExistsException if a <code>Job</code> with the same name/group already exists, andreplaceExisting is set to false.
 */
@Override public void storeJob(JobDetail newJob,boolean replaceExisting) throws ObjectAlreadyExistsException, JobPersistenceException {
  JobDetail clone=(JobDetail)newJob.clone();
  lock();
  try {
    JobWrapper jw=wrapperFactory.createJobWrapper(clone);
    if (jobFacade.containsKey(jw.getKey())) {
      if (!replaceExisting) {
        throw new ObjectAlreadyExistsException(newJob);
      }
    }
 else {
      Set<String> grpSet=toolkitDSHolder.getOrCreateJobsGroupMap(newJob.getKey().getGroup());
      grpSet.add(jw.getKey().getName());
      if (!jobFacade.hasGroup(jw.getKey().getGroup())) {
        jobFacade.addGroup(jw.getKey().getGroup());
      }
    }
    jobFacade.put(jw.getKey(),jw);
  }
  finally {
    unlock();
  }
}
