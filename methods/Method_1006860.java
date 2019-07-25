/** 
 * Add a job parameters incrementer.
 * @param jobParametersIncrementer a job parameters incrementer
 * @return this to enable fluent chaining
 */
public B incrementer(JobParametersIncrementer jobParametersIncrementer){
  properties.jobParametersIncrementer=jobParametersIncrementer;
  @SuppressWarnings("unchecked") B result=(B)this;
  return result;
}
