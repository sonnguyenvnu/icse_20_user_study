/** 
 * Unsafe version of  {@link #mOffsetMatrix}. 
 */
public static AIMatrix4x4 nmOffsetMatrix(long struct){
  return AIMatrix4x4.create(struct + AIBone.MOFFSETMATRIX);
}
