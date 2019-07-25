/** 
 * Returns whether the passed in member key maps to one of the members of the union. The key will be matched against the contained member's key returned from  {@link Member#getUnionMemberKey()}.
 * @param memberKey to check.
 * @return true if maps to an existing member of the union, false otherwise.
 */
public boolean contains(String memberKey){
  return _memberKeyToIndexMap.containsKey(memberKey);
}
