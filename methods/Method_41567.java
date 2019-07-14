/** 
 * Add a chain mapping - when the Job identified by the first key completes the job identified by the second key will be triggered.
 * @param firstJob a JobKey with the name and group of the first job
 * @param secondJob a JobKey with the name and group of the follow-up job
 */
public void addJobChainLink(JobKey firstJob,JobKey secondJob){
  if (firstJob == null || secondJob == null) {
    throw new IllegalArgumentException("Key cannot be null!");
  }
  if (firstJob.getName() == null || secondJob.getName() == null) {
    throw new IllegalArgumentException("Key cannot have a null name!");
  }
  chainLinks.put(firstJob,secondJob);
}
