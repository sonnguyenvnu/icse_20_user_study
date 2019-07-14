/** 
 * Unsafe version of  {@link #depth(int) depth}. 
 */
public static void ndepth(long struct,int value){
  UNSAFE.putInt(null,struct + XVisualInfo.DEPTH,value);
}
