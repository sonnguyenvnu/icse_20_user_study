/** 
 * <p> Determines whether or not the <code>DailyTimeIntervalTrigger</code> will occur again. </p>
 */
@Override public boolean mayFireAgain(){
  return (getNextFireTime() != null);
}
