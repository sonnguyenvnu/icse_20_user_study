/** 
 * Thread-local version of  {@link #callocDouble}. 
 */
public static DoubleBuffer stackCallocDouble(int size){
  return stackGet().callocDouble(size);
}
