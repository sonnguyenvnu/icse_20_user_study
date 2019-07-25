/** 
 * Repeatedly run this Nono at most the given number of times.
 * @param times the repeat count
 * @return the new Nono instance
 */
public final Nono repeat(long times){
  return onAssembly(new NonoRepeat(this,times));
}
