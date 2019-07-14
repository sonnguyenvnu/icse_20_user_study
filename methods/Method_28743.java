/** 
 * Return all the values in a hash. <p> <b>Time complexity:</b> O(N), where N is the total number of entries
 * @param key
 * @return All the fields values contained into a hash.
 */
@Override public List<String> hvals(final String key){
  checkIsInMultiOrPipeline();
  client.hvals(key);
  final List<String> lresult=client.getMultiBulkReply();
  return lresult;
}
