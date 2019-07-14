/** 
 * Returns true if the member with the given declaring class is unsupported. 
 */
boolean isMemberUnsupported(String className,ClassMemberKey memberKey){
  return unsupportedMembersByClass().containsEntry(className,memberKey) || unsupportedMembersByClass().containsEntry(className,ClassMemberKey.create(memberKey.identifier(),""));
}
