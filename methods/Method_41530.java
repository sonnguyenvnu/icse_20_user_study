/** 
 * Use a <code>JobKey</code> with the given name and default group to identify the JobDetail. <p>If none of the 'withIdentity' methods are set on the JobBuilder, then a random, unique JobKey will be generated.</p>
 * @param name the name element for the Job's JobKey
 * @return the updated JobBuilder
 * @see JobKey
 * @see JobDetail#getKey()
 */
public JobBuilder withIdentity(String name){
  key=new JobKey(name,null);
  return this;
}
