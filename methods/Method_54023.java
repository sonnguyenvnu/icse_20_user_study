/** 
 * Unsafe version of  {@link #mKeys(AIMeshKey.Buffer) mKeys}. 
 */
public static void nmKeys(long struct,AIMeshKey.Buffer value){
  memPutAddress(struct + AIMeshAnim.MKEYS,value.address());
  nmNumKeys(struct,value.remaining());
}
