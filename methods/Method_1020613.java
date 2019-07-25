/** 
 * Creates a  {@link WorkRunner} that runs work on Android's main thread. 
 */
public static MainThreadWorkRunner create(){
  return new MainThreadWorkRunner();
}
