/** 
 * <p> Determines whether the date and (optionally) time of the given Calendar  instance falls on a scheduled fire-time of this trigger. </p> <p> Equivalent to calling <code>willFireOn(cal, false)</code>. </p>
 * @param test the date to compare
 * @see #willFireOn(Calendar,boolean)
 */
public boolean willFireOn(Calendar test){
  return willFireOn(test,false);
}
