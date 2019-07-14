/** 
 * Unsafe version of  {@link #cRedShift(byte) cRedShift}. 
 */
public static void ncRedShift(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CREDSHIFT,value);
}
