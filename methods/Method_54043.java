/** 
 * Unsafe version of  {@link #mKeys(AIString.Buffer) mKeys}. 
 */
public static void nmKeys(long struct,AIString.Buffer value){
  memPutAddress(struct + AIMetaData.MKEYS,value.address());
}
