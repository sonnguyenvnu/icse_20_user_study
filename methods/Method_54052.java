/** 
 * Unsafe version of  {@link #mParent(AINode) mParent}. 
 */
public static void nmParent(long struct,@Nullable AINode value){
  memPutAddress(struct + AINode.MPARENT,memAddressSafe(value));
}
