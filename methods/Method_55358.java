/** 
 * Unsafe version of  {@link #dwFlags(int) dwFlags}. 
 */
public static void ndwFlags(long struct,int value){
  UNSAFE.putInt(null,struct + PIXELFORMATDESCRIPTOR.DWFLAGS,value);
}
