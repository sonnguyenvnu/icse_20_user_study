/** 
 * Unsafe version of  {@link #mUp(AIVector3D) mUp}. 
 */
public static void nmUp(long struct,AIVector3D value){
  memCopy(value.address(),struct + AICamera.MUP,AIVector3D.SIZEOF);
}
