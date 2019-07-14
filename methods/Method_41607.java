/** 
 * <p> Get the number of <code> {@link org.quartz.JobDetail}</code> s that are stored in the <code>JobsStore</code>. </p>
 */
public int getNumberOfJobs(){
synchronized (lock) {
    return jobsByKey.size();
  }
}
