/** 
 * Return the members of a set resulting from the union of all the sets hold at the specified keys. Like in  {@link #lrange(String,long,long) LRANGE} the result is sent to the client as amulti-bulk reply (see the protocol specification for more information). If just a single key is specified, then this command produces the same result as  {@link #smembers(String) SMEMBERS}. <p> Non existing keys are considered like empty sets. <p> Time complexity O(N) where N is the total number of elements in all the provided sets
 * @param keys
 * @return Multi bulk reply, specifically the list of common elements.
 */
@Override public Set<String> sunion(final String... keys){
  checkIsInMultiOrPipeline();
  client.sunion(keys);
  final List<String> members=client.getMultiBulkReply();
  if (members == null) {
    return null;
  }
  return SetFromList.of(members);
}
