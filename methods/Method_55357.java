/** 
 * Unsafe version of  {@link #nVersion(short) nVersion}. 
 */
public static void nnVersion(long struct,short value){
  UNSAFE.putShort(null,struct + PIXELFORMATDESCRIPTOR.NVERSION,value);
}
