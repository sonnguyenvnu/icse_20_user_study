/** 
 * Thread-local version of  {@link #ints(int,int)}. 
 */
public static IntBuffer stackInts(int x,int y){
  return stackGet().ints(x,y);
}
