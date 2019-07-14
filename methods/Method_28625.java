/** 
 * GETSET is an atomic set this value and return the old value command. Set key to the string value and return the old value stored at key. The string can't be longer than 1073741824 bytes (1 GB). <p> Time complexity: O(1)
 * @param key
 * @param value
 * @return Bulk reply
 */
@Override public byte[] getSet(final byte[] key,final byte[] value){
  checkIsInMultiOrPipeline();
  client.getSet(key,value);
  return client.getBinaryBulkReply();
}
