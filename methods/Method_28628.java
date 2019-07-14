/** 
 * Return all the fields and associated values in a hash. <p> <b>Time complexity:</b> O(N), where N is the total number of entries
 * @param key
 * @return All the fields and values contained into a hash.
 */
@Override public Map<byte[],byte[]> hgetAll(final byte[] key){
  checkIsInMultiOrPipeline();
  client.hgetAll(key);
  final List<byte[]> flatHash=client.getBinaryMultiBulkReply();
  final Map<byte[],byte[]> hash=new JedisByteHashMap();
  final Iterator<byte[]> iterator=flatHash.iterator();
  while (iterator.hasNext()) {
    hash.put(iterator.next(),iterator.next());
  }
  return hash;
}
