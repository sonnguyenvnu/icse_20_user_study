/** 
 * Unsafe version of  {@link #mMetadata(AIMetaData) mMetadata}. 
 */
public static void nmMetadata(long struct,@Nullable AIMetaData value){
  memPutAddress(struct + AINode.MMETADATA,memAddressSafe(value));
}
