/** 
 * Retry this Solo indefinitely if it fails.
 * @return the new Solo instance
 */
public final Solo<T> retry(){
  return onAssembly(new SoloRetry<T>(this,Long.MAX_VALUE));
}
