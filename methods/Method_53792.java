/** 
 * Unsafe version of  {@link #mOffsetMatrix(AIMatrix4x4) mOffsetMatrix}. 
 */
public static void nmOffsetMatrix(long struct,AIMatrix4x4 value){
  memCopy(value.address(),struct + AIBone.MOFFSETMATRIX,AIMatrix4x4.SIZEOF);
}
