/** 
 * Unsafe version of  {@link #dmDriverExtra(short) dmDriverExtra}. 
 */
public static void ndmDriverExtra(long struct,short value){
  UNSAFE.putShort(null,struct + DEVMODE.DMDRIVEREXTRA,value);
}
