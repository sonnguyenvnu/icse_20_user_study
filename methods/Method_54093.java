/** 
 * Unsafe version of  {@link #mMetaData(AIMetaData) mMetaData}. 
 */
public static void nmMetaData(long struct,@Nullable AIMetaData value){
  memPutAddress(struct + AIScene.MMETADATA,memAddressSafe(value));
}
