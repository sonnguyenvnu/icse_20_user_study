/** 
 * <p> Get the number of <code> {@link org.quartz.Trigger}</code> s that are stored in the <code>JobsStore</code>. </p>
 */
public int getNumberOfTriggers(){
synchronized (lock) {
    return triggersByKey.size();
  }
}
