/** 
 * Unsafe version of  {@link #dmSpecVersion(short) dmSpecVersion}. 
 */
public static void ndmSpecVersion(long struct,short value){
  UNSAFE.putShort(null,struct + DEVMODE.DMSPECVERSION,value);
}
