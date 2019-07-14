/** 
 * Unsafe version of  {@link #cbSize(int) cbSize}. 
 */
public static void ncbSize(long struct,int value){
  UNSAFE.putInt(null,struct + WNDCLASSEX.CBSIZE,value);
}
