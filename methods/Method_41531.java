/** 
 * Use a <code>JobKey</code> to identify the JobDetail. <p>If none of the 'withIdentity' methods are set on the JobBuilder, then a random, unique JobKey will be generated.</p>
 * @param jobKey the Job's JobKey
 * @return the updated JobBuilder
 * @see JobKey
 * @see JobDetail#getKey()
 */
public JobBuilder withIdentity(JobKey jobKey){
  this.key=jobKey;
  return this;
}
