/** 
 * Unsafe version of  {@link #nSize(short) nSize}. 
 */
public static void nnSize(long struct,short value){
  UNSAFE.putShort(null,struct + PIXELFORMATDESCRIPTOR.NSIZE,value);
}
