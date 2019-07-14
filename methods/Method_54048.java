/** 
 * Unsafe version of  {@link #mTransformation}. 
 */
public static AIMatrix4x4 nmTransformation(long struct){
  return AIMatrix4x4.create(struct + AINode.MTRANSFORMATION);
}
