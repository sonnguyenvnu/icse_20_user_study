/** 
 * Unsafe version of  {@link #mPosition(AIVector3D) mPosition}. 
 */
public static void nmPosition(long struct,AIVector3D value){
  memCopy(value.address(),struct + AICamera.MPOSITION,AIVector3D.SIZEOF);
}
