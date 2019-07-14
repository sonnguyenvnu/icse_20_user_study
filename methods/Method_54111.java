/** 
 * Unsafe version of  {@link #mValue(AIVector3D) mValue}. 
 */
public static void nmValue(long struct,AIVector3D value){
  memCopy(value.address(),struct + AIVectorKey.MVALUE,AIVector3D.SIZEOF);
}
