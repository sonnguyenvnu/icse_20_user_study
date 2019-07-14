/** 
 * Unsafe version of  {@link #mParent}. 
 */
@Nullable public static AINode nmParent(long struct){
  return AINode.createSafe(memGetAddress(struct + AINode.MPARENT));
}
