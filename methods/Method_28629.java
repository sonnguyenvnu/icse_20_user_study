/** 
 * Return the specified element of the list stored at the specified key. 0 is the first element, 1 the second and so on. Negative indexes are supported, for example -1 is the last element, -2 the penultimate and so on. <p> If the value stored at key is not of list type an error is returned. If the index is out of range a 'nil' reply is returned. <p> Note that even if the average time complexity is O(n) asking for the first or the last element of the list is O(1). <p> Time complexity: O(n) (with n being the length of the list)
 * @param key
 * @param index
 * @return Bulk reply, specifically the requested element
 */
@Override public byte[] lindex(final byte[] key,final long index){
  checkIsInMultiOrPipeline();
  client.lindex(key,index);
  return client.getBinaryBulkReply();
}
