/** 
 * Unsafe version of  {@link #mTransformation(AIMatrix4x4) mTransformation}. 
 */
public static void nmTransformation(long struct,AIMatrix4x4 value){
  memCopy(value.address(),struct + AINode.MTRANSFORMATION,AIMatrix4x4.SIZEOF);
}
