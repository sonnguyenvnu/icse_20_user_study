/** 
 * Return the members of a set resulting from the intersection of all the sets hold at the specified keys. Like in  {@link #lrange(String,long,long) LRANGE} the result is sent to theclient as a multi-bulk reply (see the protocol specification for more information). If just a single key is specified, then this command produces the same result as {@link #smembers(String) SMEMBERS}. Actually SMEMBERS is just syntax sugar for SINTER. <p> Non existing keys are considered like empty sets, so if one of the keys is missing an empty set is returned (since the intersection with an empty set always is an empty set). <p> Time complexity O(N*M) worst case where N is the cardinality of the smallest set and M the number of sets
 * @param keys
 * @return Multi bulk reply, specifically the list of common elements.
 */
@Override public Set<String> sinter(final String... keys){
  checkIsInMultiOrPipeline();
  client.sinter(keys);
  final List<String> members=client.getMultiBulkReply();
  if (members == null) {
    return null;
  }
  return SetFromList.of(members);
}
