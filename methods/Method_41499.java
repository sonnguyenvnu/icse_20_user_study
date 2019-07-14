/** 
 * <p> Determines whether or not the <code>CronTrigger</code> will occur again. </p>
 */
@Override public boolean mayFireAgain(){
  return (getNextFireTime() != null);
}
