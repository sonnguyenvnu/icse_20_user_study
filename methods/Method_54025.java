/** 
 * Unsafe version of  {@link #mValue(int) mValue}. 
 */
public static void nmValue(long struct,int value){
  UNSAFE.putInt(null,struct + AIMeshKey.MVALUE,value);
}
