/** 
 * Creates a fresh SoloProcessor instance.
 * @param < T > the input and output value type
 * @return the new SoloProcessor instance
 */
public static <T>SoloProcessor<T> create(){
  return new SoloProcessor<T>();
}
