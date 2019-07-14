/** 
 * <p> Store the given <code> {@link org.quartz.Job}</code>. </p>
 * @param newJob The <code>Job</code> to be stored.
 * @param replaceExisting If <code>true</code>, any <code>Job</code> existing in the <code>JobStore</code> with the same name & group should be over-written.
 * @throws ObjectAlreadyExistsException if a <code>Job</code> with the same name/group already exists, and replaceExisting is set to false.
 */
public void storeJob(JobDetail newJob,boolean replaceExisting) throws ObjectAlreadyExistsException {
  JobWrapper jw=new JobWrapper((JobDetail)newJob.clone());
  boolean repl=false;
synchronized (lock) {
    if (jobsByKey.get(jw.key) != null) {
      if (!replaceExisting) {
        throw new ObjectAlreadyExistsException(newJob);
      }
      repl=true;
    }
    if (!repl) {
      HashMap<JobKey,JobWrapper> grpMap=jobsByGroup.get(newJob.getKey().getGroup());
      if (grpMap == null) {
        grpMap=new HashMap<JobKey,JobWrapper>(100);
        jobsByGroup.put(newJob.getKey().getGroup(),grpMap);
      }
      grpMap.put(newJob.getKey(),jw);
      jobsByKey.put(jw.key,jw);
    }
 else {
      JobWrapper orig=jobsByKey.get(jw.key);
      orig.jobDetail=jw.jobDetail;
    }
  }
}
