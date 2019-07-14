/** 
 * Unsafe version of  {@link #dmSize(short) dmSize}. 
 */
public static void ndmSize(long struct,short value){
  UNSAFE.putShort(null,struct + DEVMODE.DMSIZE,value);
}
