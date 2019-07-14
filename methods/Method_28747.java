/** 
 * Return the rank (or index) or member in the sorted set at key, with scores being ordered from high to low. <p> When the given member does not exist in the sorted set, the special value 'nil' is returned. The returned rank (or index) of the member is 0-based for both commands. <p> <b>Time complexity:</b> <p> O(log(N))
 * @see #zrank(String,String)
 * @param key
 * @param member
 * @return Integer reply or a nil bulk reply, specifically: the rank of the element as an integerreply if the element exists. A nil bulk reply if there is no such element.
 */
@Override public Long zrevrank(final String key,final String member){
  checkIsInMultiOrPipeline();
  client.zrevrank(key,member);
  return client.getIntegerReply();
}
