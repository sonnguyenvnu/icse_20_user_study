/** 
 * Unsafe version of  {@link #mLookAt(AIVector3D) mLookAt}. 
 */
public static void nmLookAt(long struct,AIVector3D value){
  memCopy(value.address(),struct + AICamera.MLOOKAT,AIVector3D.SIZEOF);
}
