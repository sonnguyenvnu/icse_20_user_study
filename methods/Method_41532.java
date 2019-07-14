/** 
 * Set the given (human-meaningful) description of the Job.
 * @param jobDescription the description for the Job
 * @return the updated JobBuilder
 * @see JobDetail#getDescription()
 */
public JobBuilder withDescription(String jobDescription){
  this.description=jobDescription;
  return this;
}
