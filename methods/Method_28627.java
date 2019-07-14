/** 
 * Return all the values in a hash. <p> <b>Time complexity:</b> O(N), where N is the total number of entries
 * @param key
 * @return All the fields values contained into a hash.
 */
@Override public List<byte[]> hvals(final byte[] key){
  checkIsInMultiOrPipeline();
  client.hvals(key);
  return client.getBinaryMultiBulkReply();
}
