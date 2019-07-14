/** 
 * Unsafe version of  {@link #transientIbSize(int) transientIbSize}. 
 */
public static void ntransientIbSize(long struct,int value){
  UNSAFE.putInt(null,struct + BGFXInitLimits.TRANSIENTIBSIZE,value);
}
