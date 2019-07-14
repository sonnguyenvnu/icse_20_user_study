/** 
 * Return all the members (elements) of the set value stored at key. This is just syntax glue for {@link #sinter(String) SINTER}. <p> Time complexity O(N)
 * @param key
 * @return Multi bulk reply
 */
@Override public Set<String> smembers(final String key){
  checkIsInMultiOrPipeline();
  client.smembers(key);
  final List<String> members=client.getMultiBulkReply();
  if (members == null) {
    return null;
  }
  return SetFromList.of(members);
}
