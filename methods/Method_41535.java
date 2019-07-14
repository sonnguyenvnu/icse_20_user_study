/** 
 * Whether or not the <code>Job</code> should remain stored after it is orphaned (no <code> {@link Trigger}s</code> point to it). <p> If not explicitly set, the default value is <code>false</code>. </p>
 * @param jobDurability the value to set for the durability property.
 * @return the updated JobBuilder
 * @see JobDetail#isDurable()
 */
public JobBuilder storeDurably(boolean jobDurability){
  this.durability=jobDurability;
  return this;
}
