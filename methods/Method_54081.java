/** 
 * Unsafe version of  {@link #mRootNode(AINode) mRootNode}. 
 */
public static void nmRootNode(long struct,@Nullable AINode value){
  memPutAddress(struct + AIScene.MROOTNODE,memAddressSafe(value));
}
