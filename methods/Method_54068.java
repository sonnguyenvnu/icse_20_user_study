/** 
 * Unsafe version of  {@link #mValue(AIQuaternion) mValue}. 
 */
public static void nmValue(long struct,AIQuaternion value){
  memCopy(value.address(),struct + AIQuatKey.MVALUE,AIQuaternion.SIZEOF);
}
