/** 
 * @return Current instance.
 */
public static Input get(){
  return current.orElse(null);
}
