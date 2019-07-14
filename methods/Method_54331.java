/** 
 * Unsafe version of  {@link #transientVbSize(int) transientVbSize}. 
 */
public static void ntransientVbSize(long struct,int value){
  UNSAFE.putInt(null,struct + BGFXInitLimits.TRANSIENTVBSIZE,value);
}
