/** 
 * Add a job parameters validator.
 * @param jobParametersValidator a job parameters validator
 * @return this to enable fluent chaining
 */
public B validator(JobParametersValidator jobParametersValidator){
  properties.jobParametersValidator=jobParametersValidator;
  @SuppressWarnings("unchecked") B result=(B)this;
  return result;
}
