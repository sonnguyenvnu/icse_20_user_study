/** 
 * Unsafe version of  {@link #dwVisibleMask(int) dwVisibleMask}. 
 */
public static void ndwVisibleMask(long struct,int value){
  UNSAFE.putInt(null,struct + PIXELFORMATDESCRIPTOR.DWVISIBLEMASK,value);
}
