/** 
 * Returns a Nono that completes normally.
 * @return the new Nono instance
 */
public static Nono complete(){
  return onAssembly(NonoComplete.INSTANCE);
}
